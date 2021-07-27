package ru.interview4j.service;

import reactor.core.publisher.Flux;
import ru.interview4j.domain.Role;

public interface RoleService {

    Flux<Role> findUserRoles(Long userId);
}
