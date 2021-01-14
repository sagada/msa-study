package microservices.book.socialmultiplecation.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
 * 애플리케이션에서 곱셈을 나타내는 클래스 (a * b)
 */
@ToString
@Getter
@EqualsAndHashCode
@Entity
public final class Multiplication {

    @Id
    @GeneratedValue
    @Column(name = "MULTIPLICATION_ID")
    private Long id;
    private int factorA;
    private int factorB;

    protected Multiplication() {
    }

    public Multiplication(int factorA, int factorB) {
        this.factorA = factorA;
        this.factorB = factorB;
    }
}
