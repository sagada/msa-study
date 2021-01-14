package microservices.book.socialmultiplecation;

import com.fasterxml.jackson.databind.ObjectMapper;
import microservices.book.socialmultiplecation.controller.MultiplicationResultAttemptController;
import microservices.book.socialmultiplecation.domain.Multiplication;
import microservices.book.socialmultiplecation.domain.MultiplicationResultAttempt;
import microservices.book.socialmultiplecation.domain.User;
import microservices.book.socialmultiplecation.service.MultiplicationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class MultiplicationResultAttemptControllerTest {

    @MockBean
    private MultiplicationService multiplicationService;


    @Autowired
    private MockMvc mvc;

    private JacksonTester<MultiplicationResultAttempt> jsonResult;

    @BeforeEach
    public void setUp()
    {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void postResultReturnCorrect() throws Exception{
        genericParameterizedTest(true);
    }

    @Test
    public void postResultReturnNotCorrect() throws Exception{
        genericParameterizedTest(false);
    }


    private void genericParameterizedTest(final boolean correct) throws Exception{

        // given
        given(multiplicationService
        .checkAttempt(any(MultiplicationResultAttempt.class)))
                .willReturn(correct);

        User user = new User("json");
        Multiplication multiplication = new Multiplication(50, 70);
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3500,correct);

        // when
        MockHttpServletResponse response = mvc.perform(post("/results")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonResult.write(attempt).getJson()))
                .andReturn()
                .getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(
                        jsonResult.write(new MultiplicationResultAttempt(
                                attempt.getUser()
                                , attempt.getMultiplication()
                                , attempt.getResultAttempt()
                                , correct)
                        ).getJson()
                );
    }

}
