package ru.interview4j.rest.handler;
/*
 * Date: 28.07.2021
 * Time: 12:05 PM
 * */

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.User;
import ru.interview4j.dto.UserDto;
import ru.interview4j.rest.router.request.AuthRequest;
import ru.interview4j.service.JwtService;
import ru.interview4j.service.UserService;
import ru.interview4j.util.CustomMapper;
import ru.interview4j.validation.CredentialsValidator;

import java.util.Map;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.ServerResponse.status;

@Component
public class AuthenticationHandler {

    private final CredentialsValidator validator = new CredentialsValidator();

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


    public @NonNull Mono<ServerResponse> register(ServerRequest request) {
        Mono<AuthRequest> credentials = request.bodyToMono(AuthRequest.class).doOnNext(this::validate);
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(credentials.flatMap(userService::register)
                        .map(CustomMapper::mapToDto), UserDto.class));
    }

    private void validate(AuthRequest credentials) {
        Errors errors = new BeanPropertyBindingResult(credentials, "credentials");
        validator.validate(credentials, errors);
        if (errors.hasErrors()) {
            throw new ServerWebInputException(errors.toString());
        }
    }

    private Map<String, String> generateResponse(User user) {
        return Map.of("access_token", jwtService.buildAccessToken(user));
    }

}
