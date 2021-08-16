package ru.interview4j.service;
/*
 * Date: 02.08.2021
 * Time: 10:26 PM
 * */

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.interview4j.domain.User;
import ru.interview4j.exception.CustomException;
import ru.interview4j.repository.UserRepository;
import ru.interview4j.service.impl.UserServiceImpl;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleService roleService;

    @Test
    public void findByUsername_UsernameNotFound_ShouldThrowException() {
        given(userRepository.findByUsername(anyString())).willReturn(Mono.empty());
        Mono<UserDetails> userDetailsMono = userService.findByUsername("username");

        StepVerifier.create(userDetailsMono)
                .expectError(CustomException.class)
                .verify();
    }

    @Test
    public void findByUsername_RolesNotFound_ShouldThrowException() {
        User user = mock(User.class);
        given(userRepository.findByUsername(anyString())).willReturn(Mono.just(user));
        given(roleService.findUserRolesById(anyLong())).willReturn(Flux.empty());

        Mono<UserDetails> userDetailsMono = userService.findByUsername("username");

        StepVerifier.create(userDetailsMono)
                .expectErrorMessage("500 INTERNAL_SERVER_ERROR \"No roles found, check database\"")
                .verify();
    }

}
