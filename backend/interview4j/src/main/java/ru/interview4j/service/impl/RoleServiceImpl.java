package ru.interview4j.service.impl;
/*
 * Date: 24.07.2021
 * Time: 11:52 PM
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.interview4j.domain.Role;
import ru.interview4j.repository.RoleRepository;
import ru.interview4j.service.RoleService;

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

}
