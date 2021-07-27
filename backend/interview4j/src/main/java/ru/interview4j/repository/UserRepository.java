package ru.interview4j.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.User;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    Mono<User> findByUsername(String username);

/*
    String userWithRolesQuery = """
            select u.id, u.username,
                array(select r.name from role r join user_role ur on r.id = ur.role_id
                where ur.user_id = u.id) as roles
            from usr u where u.id = :userId
            """;
*/

}
