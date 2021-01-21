package microservices.book.socialmultiplication.event;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 이벤트를 사용하기 위한 RabbitMQ 설정
 */
@Component
public class EventDispatcher {


    private final RabbitTemplate rabbitTemplate;

    // Multiplication 관련 정보를 전달하기 위한 익스체인지
    private final String multiplicationExchange;

    // 특정 이벤트를 전송하기 위한 라우팅 키
    private final String multiplicationSolvedRoutingKey;

    @Autowired
    public EventDispatcher(final RabbitTemplate rabbitTemplate,
                           @Value("${multiplication.exchange}")  String multiplicationExchange,
                           @Value("${multiplication.solved.key}")  String multiplicationSolvedRoutingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.multiplicationExchange = multiplicationExchange;
        this.multiplicationSolvedRoutingKey = multiplicationSolvedRoutingKey;
    }

    public void send(final MultiplicationSolvedEvent multiplicationSolvedEvent) {
        rabbitTemplate.convertAndSend(
                multiplicationExchange,
                multiplicationSolvedRoutingKey,
                multiplicationSolvedEvent);
    }
}