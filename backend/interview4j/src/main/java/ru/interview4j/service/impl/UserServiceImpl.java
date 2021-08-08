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
import ru.interview4j.domain.User;
import ru.interview4j.dto.UserDto;
import ru.interview4j.exception.CustomException;
import ru.interview4j.repository.UserRepository;
import ru.interview4j.router.request.AuthRequest;
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
        Flux<User> userFlux = fetchUserRoles(userMono)
                .switchIfEmpty(Mono.error(() -> CustomException.notFound("User not found")));

        return Mono.from(userFlux).cast(UserDetails.class);
    }

    @Override
    public Mono<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Transactional
    @Override
    public Mono<User> register(AuthRequest credentials) {
        User user = new User(credentials.username(), credentials.password());
        return userRepository.save(user)
                .doOnSuccess(savedUser -> roleService.addRoleUser(savedUser.getId()).subscribe())
                .onErrorResume(throwable -> Mono.error(CustomException.unprocessableEntity()));
    }

    @Override
    public Mono<Boolean> existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getUsername(), user.getCreatedAt(), user.getUpdatedAt());
    }

    private Flux<User> fetchUserRoles(Mono<User> userMono) {
        return userMono.log()
                .flatMapMany(user -> roleService.findUserRolesById(user.getId())
                        .switchIfEmpty(Mono.error(() ->
                                CustomException.internalServerError("No roles found, check database")))
                        .collect(toSet())
                        .map(roles -> {
                            user.setRoles(roles);
                            return user;
                        }));
    }

}
