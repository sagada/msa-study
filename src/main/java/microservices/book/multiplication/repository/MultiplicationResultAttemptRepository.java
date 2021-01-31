package microservices.book.multiplication.repository;


import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MultiplicationResultAttemptRepository extends CrudRepository<MultiplicationResultAttempt, Long> {

    /**
     * @return 닉네임에 해당하는 사용자의 최근 답안 5개
     */
    List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);


}
