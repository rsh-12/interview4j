package ru.interview4j.service.impl;
/*
 * Date: 24.07.2021
 * Time: 11:22 PM
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.interview4j.domain.User;
import ru.interview4j.repository.UserRepository;
import ru.interview4j.service.RoleService;
import ru.interview4j.service.UserService;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .cast(UserDetails.class);
    }

    @Override
    public Mono<User> findUserById(Long userId) {
        Mono<User> userMono = userRepository.findById(userId);

        Flux<User> userFlux = userMono.log()
                .flatMapMany(user -> roleService.findUserRoles(userId)
                        .collect(Collectors.toSet())
                        .map(roles -> {
                            user.setRoles(roles);
                            return user;
                        })).subscribeOn(Schedulers.parallel());

        return Mono.from(userFlux).log();
    }


}
