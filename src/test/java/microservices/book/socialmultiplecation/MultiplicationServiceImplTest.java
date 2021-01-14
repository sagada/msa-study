package microservices.book.socialmultiplecation;

import microservices.book.socialmultiplecation.domain.Multiplication;
import microservices.book.socialmultiplecation.domain.MultiplicationResultAttempt;
import microservices.book.socialmultiplecation.domain.User;
import microservices.book.socialmultiplecation.respository.MultiplicationResultAttemptRepository;
import microservices.book.socialmultiplecation.respository.UserRepository;
import microservices.book.socialmultiplecation.service.MultiplicationServiceImpl;
import microservices.book.socialmultiplecation.service.RandomGeneratorService;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class MultiplicationServiceImplTest {

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Mock
    private MultiplicationServiceImpl multiplicationServiceImpl;

    @Mock
    private MultiplicationResultAttemptRepository attemptRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp()
    {
        multiplicationServiceImpl = new MultiplicationServiceImpl(randomGeneratorService, attemptRepository, userRepository);
    }

    @Test
    public void checkCorrectAttemptTest()
    {
        // given
        Multiplication multiplication = new Multiplication(50 , 60);
        User user = new User("Jone_doe");

        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000, false);
        MultiplicationResultAttempt verifiedAttempt = new MultiplicationResultAttempt(user, multiplication, 3000, true);

        given(userRepository.findByAlias("john_doe")).willReturn(Optional.empty());

        // when
        boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);

        // assert
        assertThat(attemptResult).isTrue();
        verify(attemptRepository).save(verifiedAttempt);
    }

}
