package ru.interview4j.service;
/*
 * Date: 02.08.2021
 * Time: 10:26 PM
 * */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.interview4j.exception.CustomException;
import ru.interview4j.repository.UserRepository;
import ru.interview4j.service.impl.UserServiceImpl;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
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

}
