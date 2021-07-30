package ru.interview4j.repository;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.ERole;
import ru.interview4j.domain.Role;

public interface RoleRepository extends ReactiveCrudRepository<Role, Long> {

    @Query("select id, name from role join user_role ur on role.id = ur.role_id where user_id = :userId")
    Flux<Role> findRolesByUserId(@Param("userId") Long id);

    Mono<Role> findRoleByName(ERole name);

    Mono<Boolean> existsByName(ERole name);

    @Modifying
    @Query("insert into user_role(user_id, role_id) values($1, $2)")
    Mono<Void> saveUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

}
