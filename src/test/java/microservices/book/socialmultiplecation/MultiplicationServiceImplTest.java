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
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.util.List;
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

    @Test
    public void retriveStatsTest()
    {
        // given
        Multiplication multiplication = new Multiplication(50 , 60);
        User user = new User("Jone_doe");

        MultiplicationResultAttempt attempt1 = new MultiplicationResultAttempt(user, multiplication, 3000, false);
        MultiplicationResultAttempt attempt2 = new MultiplicationResultAttempt(user, multiplication, 3051, false);

        List<MultiplicationResultAttempt> lastestAttempts = Lists.newArrayList(attempt1, attempt2);
        given(userRepository.findByAlias("jon")).willReturn(Optional.empty());
        given(attemptRepository.findTop5ByUserAliasOrderByIdDesc("jon")).willReturn(lastestAttempts);


        // when
        List<MultiplicationResultAttempt> last = multiplicationServiceImpl.getStatsForUser("jon");

        //then
        assertThat(last).isEqualTo(lastestAttempts);
    }

}
