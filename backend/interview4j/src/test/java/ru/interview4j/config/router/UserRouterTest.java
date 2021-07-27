package ru.interview4j.config.router;
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
import ru.interview4j.domain.ERole;
import ru.interview4j.domain.Role;
import ru.interview4j.domain.User;
import ru.interview4j.repository.RoleRepository;
import ru.interview4j.repository.UserRepository;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRouterTest {

    @Autowired
    private WebTestClient testClient;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @Test
    public void findUserById() {
        User user = new User("username", "password");
        user.setId(1L);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        Role role = new Role(ERole.ROLE_USER);
        role.setId(1L);

        given(roleRepository.findRolesByUserId(anyLong())).willReturn(Flux.just(role));
        given(userRepository.findById(anyLong())).willReturn(Mono.just(user));

        testClient.get().uri("/api/users/1")
                .accept(APPLICATION_JSON).exchange()
                .expectStatus().isOk();
    }

}
