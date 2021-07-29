package ru.interview4j.service.impl;
/*
 * Date: 24.07.2021
 * Time: 11:22 PM
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.interview4j.domain.Role;
import ru.interview4j.domain.User;
import ru.interview4j.dto.RoleDto;
import ru.interview4j.dto.UserDto;
import ru.interview4j.repository.UserRepository;
import ru.interview4j.service.RoleService;
import ru.interview4j.service.UserService;

import java.util.Set;

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
        Flux<User> userFlux = fetchUserRoles(userMono);

        return Mono.from(userFlux).cast(UserDetails.class);
    }

    @Override
    public Mono<UserDto> findUserById(Long userId) {
        Mono<User> userMono = userRepository.findById(userId);
        Flux<UserDto> userDtoFlux = fetchRoles(userMono);

        return Mono.from(userDtoFlux).log();
    }

    @Transactional
    @Override
    public Mono<User> register(User user) {
        return userRepository.save(user)
                .doOnSuccess(savedUser -> roleService.addRoleUser(user.getId()).subscribe())
                .onErrorResume(e -> e instanceof Exception, e -> Mono.empty());
    }

    @Override
    public Mono<Boolean> existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    private Flux<UserDto> fetchRoles(Mono<User> userMono) {
        return userMono.log()
                .flatMapMany(user -> roleService.findUserRoles(user.getId())
                        .collect(toSet())
                        .map(roles -> {
                            user.setRoles(roles);
                            return user;
                        })
                        .map(this::mapToUserDto)).subscribeOn(Schedulers.parallel());
    }

    private Flux<User> fetchUserRoles(Mono<User> userMono) {
        return userMono.log()
                .flatMapMany(user -> roleService.findUserRoles(user.getId())
                        .collect(toSet())
                        .map(roles -> {
                            user.setRoles(roles);
                            return user;
                        }));
    }

    private UserDto mapToUserDto(User user) {
        return new UserDto(user.getUsername(), user.getCreatedAt(), user.getUpdatedAt());
    }

    private Set<RoleDto> mapToRoleDto(Set<Role> roles) {
        return roles.stream()
                .map(role -> new RoleDto(role.getName()))
                .collect(toSet());
    }

}
