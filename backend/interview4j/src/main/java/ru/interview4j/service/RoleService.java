package ru.interview4j.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.Role;

public interface RoleService {

    Flux<Role> findUserRolesById(Long userId);

    Mono<Void> addRoleUser(Long userId);
}
