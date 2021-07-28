package ru.interview4j.handler;
/*
 * Date: 28.07.2021
 * Time: 12:05 PM
 * */

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.User;
import ru.interview4j.router.request.LoginRequest;
import ru.interview4j.service.JwtService;
import ru.interview4j.service.UserService;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.ServerResponse.status;

@Component
public class AuthenticationHandler {

    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationHandler(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    public @NonNull Mono<ServerResponse> login(ServerRequest request) {
        Mono<LoginRequest> credentialsMono = request.bodyToMono(LoginRequest.class);

        return credentialsMono.flatMap(credentials ->
                userService.findByUsername(credentials.username()).cast(User.class)
                        .flatMap(user -> credentials.password().equals(user.getPassword())
                                ? ok().body(fromValue(jwtService.buildAccessToken(user)))
                                : status(401).body(fromValue("Unauthorized")))
        );

    }
}
