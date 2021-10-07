package ru.interview4j.rest.router;
/*
 * Date: 05.08.2021
 * Time: 10:12 AM
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.interview4j.rest.handler.SectionHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class SectionRouter {

    private final static String API_SECTIONS = "/api/sections";

    private final SectionHandler sectionHandler;

    @Autowired
    public SectionRouter(SectionHandler sectionHandler) {
        this.sectionHandler = sectionHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> sectionRoutes() {
        return route(GET(API_SECTIONS + "/{id}").and(accept(APPLICATION_JSON)), sectionHandler::getSectionById)
                .andRoute(GET(API_SECTIONS).and(accept(APPLICATION_JSON)), sectionHandler::getSections);
    }


}
