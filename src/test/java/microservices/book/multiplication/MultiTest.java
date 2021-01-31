package microservices.book.multiplication;

import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MultiTest {
//
//    @Mock
//    private RandomGeneratorService randomGeneratorService;
//
//    @Mock
//    MultiplicationServiceImpl multiplicationService;
//
//    @Mock
//    MultiplicationResultAttemptRepository attemptRepository;
//
//    @Mock
//    EventDispatcher eventDispatcher;
//    @Mock
//    UserRepository userRepository;
//
//    @BeforeEach
//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//        multiplicationService = new MultiplicationServiceImpl(randomGeneratorService, attemptRepository, userRepository, eventDispatcher);
//    }
//
//    @Test
//    public void createRandomMultiplicationTest()
//    {
//        // given
//        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);
//
//        // when
//        Multiplication multiplication = multiplicationService.createRandomMultiplication();
//
//        // assert
//        assertThat(multiplication.getFactorA()).isEqualTo(50);
//        assertThat(multiplication.getFactorB()).isEqualTo(30);
//
//    }
//
//    @Test
//    public void generateRandomFactorIsBetweenExpectedLimits() throws Exception
//    {
//        List<Integer> randomFactors =  IntStream.range(0, 1000).map(i-> randomGeneratorService.generateRandomFactor())
//                .boxed()
//                .collect(Collectors.toList());
//        randomFactors.forEach(System.out::println);
//    }
//
//    @Test
//    public void checkCorrectAttemptTest()
//    {
//        // given
//        Multiplication multiplication = new Multiplication(50, 60);
//        User user = new User("backJoon");
//        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000, false);
//
//        // when
//        boolean attemptResult = multiplicationService.checkAttempt(attempt);
//
//        // assert
//        assertThat(attemptResult).isTrue();
//    }
//
//    @Test
//    public void checkWrongAttemptTest()
//    {
//        // given
//        Multiplication multiplication = new Multiplication(50, 60);
//        User user = new User("backJoon");
//        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000, false);
//
//        // when
//        boolean attemptResult = multiplicationService.checkAttempt(attempt);
//
//        // assert
//        assertThat(attemptResult).isFalse();
//    }

}

