package microservices.book.socialmultiplication.controller;

import lombok.extern.slf4j.Slf4j;
import microservices.book.socialmultiplication.domain.MultiplicationResultAttempt;
import microservices.book.socialmultiplication.domain.User;
import microservices.book.socialmultiplication.respository.UserRepository;
import microservices.book.socialmultiplication.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/results")
public class MultiplicationResultAttemptController {

    private final MultiplicationService multiplicationService;
    private final UserRepository userRepository;

    @Autowired
    public MultiplicationResultAttemptController(MultiplicationService multiplicationService, UserRepository userRepository)
    {
        this.multiplicationService = multiplicationService;
        this.userRepository = userRepository;
    }

    @PostMapping
    ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody MultiplicationResultAttempt multiplicationResultAttempt)
    {
        log.info("user Alias  {} ", multiplicationResultAttempt.getUser().getAlias());

        User findUser = userRepository.findByAlias(multiplicationResultAttempt.getUser().getAlias())
                .orElseThrow(()-> new IllegalArgumentException("없는 유저입니다."));

        boolean isCorrect = multiplicationService.checkAttempt(multiplicationResultAttempt);

        MultiplicationResultAttempt attemptCopy = new MultiplicationResultAttempt(
                findUser,
                multiplicationResultAttempt.getMultiplication(),
                multiplicationResultAttempt.getResultAttempt(),
                isCorrect
        );

        return ResponseEntity.ok(attemptCopy);
    }

    @GetMapping
    ResponseEntity<List<MultiplicationResultAttempt>> getStatistList(@RequestParam("alias") String alias)
    {
        log.info("getStatistList alias : {}", alias);
        return ResponseEntity.ok(multiplicationService.getStatsForUser(alias));
    }

    @GetMapping("/{resultId}")
    public ResponseEntity<MultiplicationResultAttempt> getResultById(final @PathVariable("resultId") Long resultId){
        return ResponseEntity.ok(multiplicationService.getResultById(resultId));
    }

}
