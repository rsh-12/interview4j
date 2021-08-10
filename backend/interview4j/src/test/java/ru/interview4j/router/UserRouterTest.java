package ru.interview4j.router;
/*
 * Date: 27.07.2021
 * Time: 3:42 PM
 * */

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.User;
import ru.interview4j.exception.CustomException;
import ru.interview4j.service.UserService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON;

public class UserRouterTest extends AbstractRouterTestClass {

    private static final String API_USERS = "/api/users";

    @MockBean
    private UserService userService;

    @Disabled
    @Test
    public void findUserById_ShouldReturnUserDto() {
        User user = mock(User.class);
        given(userService.findUserById(anyLong())).willReturn(Mono.just(user));

        webClient.get().uri(API_USERS + "/1")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("username").exists()
                .jsonPath("createdAt").exists()
                .jsonPath("updatedAt").exists();
    }

    @Test
    public void findUserById_ShouldReturnNotFound() {
        given(userService.findUserById(anyLong())).willThrow(CustomException.notFound("User not found"));

        webClient.get().uri(API_USERS + "/1")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

}
