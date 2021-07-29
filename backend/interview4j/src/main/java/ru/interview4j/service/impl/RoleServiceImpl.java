package ru.interview4j.service.impl;
/*
 * Date: 24.07.2021
 * Time: 11:52 PM
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.ERole;
import ru.interview4j.domain.Role;
import ru.interview4j.repository.RoleRepository;
import ru.interview4j.service.RoleService;

import java.time.Duration;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Flux<Role> findUserRoles(Long userId) {
        return roleRepository.findRolesByUserId(userId);
    }

    @Transactional
    @Override
    public Mono<Void> addRoleUser(Long userId) {
        return roleRepository.findRoleByName(ERole.ROLE_USER)
                .flatMap(role -> roleRepository.saveUserRole(userId, role.getId()))
                .delaySubscription(Duration.ofMillis(250))
                .onErrorResume(e -> e instanceof Exception, e -> Mono.empty());
    }

}
