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

import static java.util.stream.Collectors.toSet;

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
        Mono<User> userMono = userRepository.findByUsername(username);
        Flux<User> userFlux = fetchRoles(userMono);

        return Mono.from(userFlux).cast(UserDetails.class);
    }

    @Override
    public Mono<User> findUserById(Long userId) {
        Mono<User> userMono = userRepository.findById(userId);
        Flux<User> userFlux = fetchRoles(userMono);

        return Mono.from(userFlux).log();
    }

    private Flux<User> fetchRoles(Mono<User> userMono) {
        return userMono
                .log()
                .flatMapMany(user -> roleService.findUserRoles(user.getId())
                        .collect(toSet())
                        .map(roles -> {
                            user.setRoles(roles);
                            return user;
                        })).subscribeOn(Schedulers.parallel());
    }


}
