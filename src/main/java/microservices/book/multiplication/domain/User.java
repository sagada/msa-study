package microservices.book.multiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
 * 사용자 정보를 저장하는 클래스
 */
@ToString(of = {"id", "alias"})
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@Entity
public final class User {

    @Id
    @GeneratedValue
    @Column(name= "USER_ID")
    private Long id;

    private final String alias;

    public User() {
        alias = null;
    }
}
