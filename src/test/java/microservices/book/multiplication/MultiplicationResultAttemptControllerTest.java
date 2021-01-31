package microservices.book.multiplication;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class MultiplicationResultAttemptControllerTest {
//
//    @MockBean
//    private MultiplicationService multiplicationService;
//
//
//    @Autowired
//    private MockMvc mvc;
//
//    private JacksonTester<MultiplicationResultAttempt> jsonResult;
//
//    @BeforeEach
//    public void setUp()
//    {
//        JacksonTester.initFields(this, new ObjectMapper());
//    }
//
//    @Test
//    public void postResultReturnCorrect() throws Exception{
//        genericParameterizedTest(true);
//    }
//
//    @Test
//    public void postResultReturnNotCorrect() throws Exception{
//        genericParameterizedTest(false);
//    }
//
//
//    private void genericParameterizedTest(final boolean correct) throws Exception{
//
//        // given
//        given(multiplicationService
//        .checkAttempt(any(MultiplicationResultAttempt.class)))
//                .willReturn(correct);
//
//        User user = new User("json");
//        Multiplication multiplication = new Multiplication(50, 70);
//        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3500,correct);
//
//        // when
//        MockHttpServletResponse response = mvc.perform(post("/results")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonResult.write(attempt).getJson()))
//                .andReturn()
//                .getResponse();
//
//        // then
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString())
//                .isEqualTo(
//                        jsonResult.write(new MultiplicationResultAttempt(
//                                attempt.getUser()
//                                , attempt.getMultiplication()
//                                , attempt.getResultAttempt()
//                                , correct)
//                        ).getJson()
//                );
//    }

}
