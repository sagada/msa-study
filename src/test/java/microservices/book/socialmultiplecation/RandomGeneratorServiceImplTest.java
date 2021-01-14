package microservices.book.socialmultiplecation;

import microservices.book.socialmultiplecation.service.RandomGeneratorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomGeneratorServiceImplTest {
    private RandomGeneratorServiceImpl randomGeneratorService;

    @BeforeEach
    public void setUp(){
        randomGeneratorService = new RandomGeneratorServiceImpl();
    }

    @Test
    public void  generateRandomFactorIsBetweenExpectedLimits() throws Exception{
        List<Integer> randomFactors =  IntStream.range(0, 1000).map(i-> randomGeneratorService.generateRandomFactor())
                .boxed()
                .collect(Collectors.toList());

    }
}
