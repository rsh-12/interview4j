package ru.interview4j.config;
/*
 * Date: 25.07.2021
 * Time: 12:15 AM
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.interview4j.handler.UserHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouter {

    private final UserHandler userHandler;

    @Autowired
    public UserRouter(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return route(GET("/api/users/{id}").and(accept(APPLICATION_JSON)),
                userHandler::getUserById);
    }
}
