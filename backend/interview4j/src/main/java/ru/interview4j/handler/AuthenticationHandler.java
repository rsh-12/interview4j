package ru.interview4j.handler;
/*
 * Date: 28.07.2021
 * Time: 12:05 PM
 * */

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.User;
import ru.interview4j.router.request.AuthRequest;
import ru.interview4j.service.JwtService;
import ru.interview4j.service.UserService;

import java.util.Map;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
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
        Mono<AuthRequest> loginRequest = request.bodyToMono(AuthRequest.class);
        Mono<ServerResponse> unauthorized = status(HttpStatus.UNAUTHORIZED).build();

        return loginRequest.flatMap(credentials ->
                userService.findByUsername(credentials.username()).cast(User.class)
                        .flatMap(user -> credentials.password().equals(user.getPassword())
                                ? ok().body(fromValue(generateResponse(user))) : unauthorized));
    }


    public Mono<ServerResponse> register(ServerRequest request) {
        Mono<AuthRequest> registerRequest = request.bodyToMono(AuthRequest.class);

        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(registerRequest.flatMap(candidate -> {
                    User user = new User(candidate.username(), candidate.password());
                    return userService.register(user);
                }), User.class))
                .switchIfEmpty(ServerResponse.badRequest().build());
    }

    private Map<String, String> generateResponse(User user) {
        return Map.of("access_token", jwtService.buildAccessToken(user));
    }

}
