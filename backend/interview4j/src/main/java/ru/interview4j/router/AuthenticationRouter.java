package ru.interview4j.router;
/*
 * Date: 28.07.2021
 * Time: 12:02 PM
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.interview4j.handler.AuthenticationHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AuthenticationRouter {

    private final AuthenticationHandler authHandler;

    @Autowired
    public AuthenticationRouter(AuthenticationHandler authHandler) {
        this.authHandler = authHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> authRoutes() {
        return route(POST("/api/auth/login"), authHandler::login);
    }

}
