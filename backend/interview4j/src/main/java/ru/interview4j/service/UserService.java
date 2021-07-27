package ru.interview4j.service;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import reactor.core.publisher.Mono;
import ru.interview4j.dto.UserDto;

public interface UserService extends ReactiveUserDetailsService {

    Mono<UserDto> findUserById(Long userId);
}
