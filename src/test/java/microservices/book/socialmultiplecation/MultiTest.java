package microservices.book.socialmultiplecation;

import microservices.book.socialmultiplecation.domain.Multiplication;
import microservices.book.socialmultiplecation.domain.MultiplicationResultAttempt;
import microservices.book.socialmultiplecation.domain.User;
import microservices.book.socialmultiplecation.respository.MultiplicationResultAttemptRepository;
import microservices.book.socialmultiplecation.respository.UserRepository;
import microservices.book.socialmultiplecation.service.MultiplicationServiceImpl;
import microservices.book.socialmultiplecation.service.RandomGeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class MultiTest {

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Mock
    MultiplicationServiceImpl multiplicationService;

    @Mock
    MultiplicationResultAttemptRepository attemptRepository;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        multiplicationService = new MultiplicationServiceImpl(randomGeneratorService, attemptRepository, userRepository);
    }

    @Test
    public void createRandomMultiplicationTest()
    {
        // given
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

        // when
        Multiplication multiplication = multiplicationService.createRandomMultiplication();

        // assert
        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);

    }

    @Test
    public void generateRandomFactorIsBetweenExpectedLimits() throws Exception
    {
        List<Integer> randomFactors =  IntStream.range(0, 1000).map(i-> randomGeneratorService.generateRandomFactor())
                .boxed()
                .collect(Collectors.toList());
        randomFactors.forEach(System.out::println);
    }

    @Test
    public void checkCorrectAttemptTest()
    {
        // given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("backJoon");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000, false);

        // when
        boolean attemptResult = multiplicationService.checkAttempt(attempt);

        // assert
        assertThat(attemptResult).isTrue();
    }

    @Test
    public void checkWrongAttemptTest()
    {
        // given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("backJoon");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000, false);

        // when
        boolean attemptResult = multiplicationService.checkAttempt(attempt);

        // assert
        assertThat(attemptResult).isFalse();
    }

}

