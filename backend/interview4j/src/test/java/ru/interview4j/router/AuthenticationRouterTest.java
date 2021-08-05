package ru.interview4j.router;
/*
 * Date: 30.07.2021
 * Time: 10:04 AM
 * */

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.User;
import ru.interview4j.dto.UserDto;
import ru.interview4j.router.request.AuthRequest;
import ru.interview4j.service.UserService;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationRouterTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private UserService userService;

    private static User MOCK_USER;
    private static final LocalDateTime CREATED_AT_NOW = LocalDateTime.now();
    private static final LocalDateTime UPDATED_AT_NOW = LocalDateTime.now();
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password12345";

    @Before
    public void setUp() throws Exception {
        MOCK_USER = mock(User.class);
    }

    @Test
    public void register_ShouldReturnUserDto() {
        given(userService.register(any())).willReturn(Mono.just(MOCK_USER));
        given(userService.mapToUserDto(any())).willReturn(
                new UserDto(USERNAME, CREATED_AT_NOW, UPDATED_AT_NOW));

        AuthRequest credentials = new AuthRequest(USERNAME, PASSWORD);
        webClient.post().uri("/api/auth/register")
                .accept(APPLICATION_JSON)
                .body(Mono.just(credentials), AuthRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("username").isEqualTo(USERNAME);
    }

}
