package ru.interview4j.rest.router;
/*
 * Date: 08.08.2021
 * Time: 12:49 PM
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.interview4j.rest.handler.QuestionHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class QuestionRouter {

    private final static String API_QUESTIONS = "/api/sections/{sectionId}/questions";

    private final QuestionHandler questionHandler;

    @Autowired
    public QuestionRouter(QuestionHandler questionHandler) {
        this.questionHandler = questionHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> questionRoutes() {
        return route(GET(API_QUESTIONS + "/{id}").and(accept(APPLICATION_JSON)),
                questionHandler::getQuestionById);
    }

}
