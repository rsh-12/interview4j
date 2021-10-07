package ru.interview4j.rest.handler;
/*
 * Date: 25.07.2021
 * Time: 12:16 AM
 * */


import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.interview4j.dto.UserDto;
import ru.interview4j.service.UserService;
import ru.interview4j.util.CustomMapper;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;


@Component
public class UserHandler {

    private final UserService userService;

    @Autowired
    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public @NonNull Mono<ServerResponse> getUserById(ServerRequest request) {
        Long userId = Long.valueOf(request.pathVariable("id"));
        Mono<UserDto> user = userService.findUserById(userId)
                .map(CustomMapper::mapToDto);

        return user.flatMap(userDto -> ServerResponse
                        .ok().contentType(APPLICATION_JSON)
                        .body(fromValue(userDto)));
    }

}
