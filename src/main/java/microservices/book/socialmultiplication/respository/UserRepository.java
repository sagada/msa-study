package microservices.book.socialmultiplication.respository;

import org.springframework.data.repository.CrudRepository;
import microservices.book.socialmultiplication.domain.User;
import java.util.Optional;

/*
 * {@link User}를 저장하고 조회하기 위한 인터페이스
 */
public interface UserRepository extends CrudRepository<User, Long> {
   Optional<User> findByAlias(final String alias);
}