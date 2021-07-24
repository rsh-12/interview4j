package ru.interview4j.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ru.interview4j.domain.Role;

public interface RoleRepository extends ReactiveCrudRepository<Role, Long> {

    @Query("select name from role join usr_role ur on role.id = ur.role_id where usr_id = :userId")
    Flux<Role> findUserRoles(Long userId);
}
