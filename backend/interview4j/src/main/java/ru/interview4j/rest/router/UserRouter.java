package ru.interview4j.rest.router;
/*
 * Date: 25.07.2021
 * Time: 12:15 AM
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.interview4j.rest.handler.UserHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouter {

    private final static String API_USERS = "/api/users";

    private final UserHandler userHandler;

    @Autowired
    public UserRouter(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> userRoutes() {
        return route(GET(API_USERS + "/{id}").and(accept(APPLICATION_JSON)),
                userHandler::getUserById);
    }
}
