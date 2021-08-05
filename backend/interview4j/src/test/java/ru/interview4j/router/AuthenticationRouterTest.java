package ru.interview4j.router;
/*
 * Date: 30.07.2021
 * Time: 10:04 AM
 * */

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.User;
import ru.interview4j.dto.UserDto;
import ru.interview4j.router.request.AuthRequest;
import ru.interview4j.service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON;

public class AuthenticationRouterTest extends AbstractRouterTestClass {

    @MockBean
    private UserService userService;

    private static User MOCK_USER;

    @Before
    public void setUp() {
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
