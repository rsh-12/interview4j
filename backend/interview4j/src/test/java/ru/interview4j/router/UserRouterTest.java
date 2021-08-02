package ru.interview4j.router;
/*
 * Date: 27.07.2021
 * Time: 3:42 PM
 * */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.Role;
import ru.interview4j.domain.User;
import ru.interview4j.repository.RoleRepository;
import ru.interview4j.repository.UserRepository;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRouterTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @Test
    public void findUserById() {
        User user = mock(User.class);
        Role role = mock(Role.class);

        given(user.getUsername()).willReturn("john");

        given(roleRepository.findRolesByUserId(anyLong())).willReturn(Flux.just(role));
        given(userRepository.findById(anyLong())).willReturn(Mono.just(user));

        webClient.get().uri("/api/users/1")
                .accept(APPLICATION_JSON).exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("username").exists();
    }

}
