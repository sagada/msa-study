package microservices.book.multiplication;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class MultiplicationControllerTest {

    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<Multiplication> json;

//    @BeforeEach
//    public void setUp()
//    {
//        JacksonTester.initFields(this, new ObjectMapper());
//    }
//
//    @Test
//    public void getRandomMultiplicationTest() throws Exception{
//
//        // given
//        given(multiplicationService.createRandomMultiplication()).willReturn(new Multiplication(70, 20));
//
//        // when
//        MockHttpServletResponse response = mvc.perform(
//                get("/multiplications/random")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // then
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString())
//                .isEqualTo(json.write(new Multiplication(70, 20)).getJson());
//
//    }
}
