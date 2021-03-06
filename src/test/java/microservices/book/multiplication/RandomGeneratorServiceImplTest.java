package microservices.book.multiplication;

import microservices.book.multiplication.service.RandomGeneratorServiceImpl;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
@SpringBootTest
public class RandomGeneratorServiceImplTest {
    private RandomGeneratorServiceImpl randomGeneratorService;

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
