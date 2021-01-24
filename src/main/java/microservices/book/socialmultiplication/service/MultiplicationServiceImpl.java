package microservices.book.socialmultiplication.service;

import microservices.book.socialmultiplication.domain.Multiplication;
import microservices.book.socialmultiplication.domain.MultiplicationResultAttempt;
import microservices.book.socialmultiplication.domain.User;
import microservices.book.socialmultiplication.event.EventDispatcher;
import microservices.book.socialmultiplication.event.MultiplicationSolvedEvent;
import microservices.book.socialmultiplication.respository.MultiplicationResultAttemptRepository;
import microservices.book.socialmultiplication.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {

    private final RandomGeneratorService randomGeneratorService;
    private final MultiplicationResultAttemptRepository attemptRepository;
    private final UserRepository userRepository;
    private final EventDispatcher eventDispatcher;

    @Autowired
    public MultiplicationServiceImpl(
            RandomGeneratorService randomGeneratorService,
            MultiplicationResultAttemptRepository attemptRepository,
            UserRepository userRepository,
            EventDispatcher eventDispatcher)
    {
        this.randomGeneratorService = randomGeneratorService;
        this.attemptRepository = attemptRepository;
        this.userRepository = userRepository;
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public Multiplication createRandomMultiplication()
    {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }

    @Transactional
    @Override
    public boolean checkAttempt(final MultiplicationResultAttempt attempt)
    {
        // 해당 닉네임의 사용자가 존재하는지 확인
        User user = userRepository.findByAlias(attempt.getUser().getAlias()).orElseGet(attempt::getUser);

        // 조작된 답안을 방지
        Assert.isTrue(!attempt.isCorrect(), "채점한 상태로 보낼 수 없습니다!!");

        // 답안을 채점
        boolean isCorrect = attempt.getResultAttempt() ==
                attempt.getMultiplication().getFactorA() *
                        attempt.getMultiplication().getFactorB();


        MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(
                user,
                attempt.getMultiplication(),
                attempt.getResultAttempt(),
                isCorrect
        );

        // 답안을 저장
        attemptRepository.save(checkedAttempt);

        // 이벤트로 결과를 전송
        eventDispatcher.send(
                new MultiplicationSolvedEvent(checkedAttempt.getId(),
                        user.getId(),
                        checkedAttempt.isCorrect())
        );

        return isCorrect;
    }

    @Override
    public List<MultiplicationResultAttempt> getStatsForUser(final String userAlias)
    {
        return attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
    }

    @Override
    public Optional<MultiplicationResultAttempt> getResultById(Long resultId)
    {
        return attemptRepository.findById(resultId);
    }

}
