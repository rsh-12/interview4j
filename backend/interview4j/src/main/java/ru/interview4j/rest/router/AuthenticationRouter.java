package ru.interview4j.rest.router;
/*
 * Date: 28.07.2021
 * Time: 12:02 PM
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.interview4j.rest.handler.AuthenticationHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AuthenticationRouter {

    private final static String API_AUTH = "/api/auth";

    private final AuthenticationHandler authHandler;

    @Autowired
    public AuthenticationRouter(AuthenticationHandler authHandler) {
        this.authHandler = authHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> authRoutes() {
        return route(POST(API_AUTH + "/login").and(accept(APPLICATION_JSON)), authHandler::login)
                .andRoute(POST(API_AUTH + "/register").and(accept(APPLICATION_JSON)), authHandler::register);
    }

}
