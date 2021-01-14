package microservices.book.socialmultiplecation.service;

import microservices.book.socialmultiplecation.domain.Multiplication;
import microservices.book.socialmultiplecation.domain.MultiplicationResultAttempt;

public interface MultiplicationService {
    /*
     * 두 개의 무작위 인수를 담은 {@link Multiplication} 객체를 생성한다.
     * 무작위로 생성되는 숫자의 범위는 1 ~ 99
     * @return 무작위 인수를 담은 {@link MMultiplication} 객체
     */
    Multiplication createRandomMultiplication();

    /*
     * @return 곱셈 계산 결과가 맞으면 true, 아니면 false
     */
    boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);
}
