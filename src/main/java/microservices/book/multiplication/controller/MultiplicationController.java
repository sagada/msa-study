package microservices.book.multiplication.controller;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiplicationController {

    private final MultiplicationService multiplicationService;

    @Autowired
    public MultiplicationController(final MultiplicationService multiplicationService)
    {
        this.multiplicationService = multiplicationService;
    }

    @GetMapping("/multiplications/random")
    Multiplication getRandomMultiplication()
    {
        return multiplicationService.createRandomMultiplication();
    }


}
