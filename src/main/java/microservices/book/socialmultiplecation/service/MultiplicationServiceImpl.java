package microservices.book.socialmultiplecation.service;

import microservices.book.socialmultiplecation.domain.Multiplication;
import microservices.book.socialmultiplecation.domain.MultiplicationResultAttempt;
import microservices.book.socialmultiplecation.domain.User;
import microservices.book.socialmultiplecation.respository.MultiplicationRepository;
import microservices.book.socialmultiplecation.respository.MultiplicationResultAttemptRepository;
import microservices.book.socialmultiplecation.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.beans.Transient;
import java.util.Optional;

@Service
public class MultiplicationServiceImpl implements MultiplicationService{

    private final RandomGeneratorService randomGeneratorService;
    private final UserRepository userRepository;
    private final MultiplicationResultAttemptRepository attemptRepository;

    @Autowired
    public MultiplicationServiceImpl(
            RandomGeneratorService randomGeneratorService
            , MultiplicationResultAttemptRepository attemptRepository
            , UserRepository userRepository)
    {
        this.randomGeneratorService = randomGeneratorService;
        this.userRepository = userRepository;
        this.attemptRepository = attemptRepository;
    }

    @Override
    public Multiplication createRandomMultiplication()
    {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();

        return new Multiplication(factorA, factorB);
    }

    @Transient
    @Override
    public boolean checkAttempt(MultiplicationResultAttempt resultAttempt)
    {
        // 해당 닉네임의 사용자가 존재하는지 확인
        Optional<User> user = userRepository.findByAlias(resultAttempt.getUser().getAlias());

        // 조작 된 답안을 방지
        Assert.isTrue(!resultAttempt.isCorrect(), "채점한 상태로 보낼 수 없습니다.");

        // 답안을 채점
        boolean correct =  resultAttempt.getResultAttempt() ==
                resultAttempt.getMultiplication().getFactorA() * resultAttempt.getMultiplication().getFactorB();

        // 복사본을 만들고 correct 필드를 상황에 맞게 설정
        MultiplicationResultAttempt checkdAttempt = new MultiplicationResultAttempt(
                user.orElse(resultAttempt.getUser())
                , resultAttempt.getMultiplication()
                , resultAttempt.getResultAttempt()
                , correct);

        attemptRepository.save(checkdAttempt);
        return correct;
    }
}
