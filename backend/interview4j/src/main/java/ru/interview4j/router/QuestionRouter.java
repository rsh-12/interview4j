package ru.interview4j.router;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import ru.interview4j.handler.QuestionHandler;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
@Slf4j
public class QuestionRouter {

    private final QuestionHandler questionHandler;

    public QuestionRouter(QuestionHandler questionHandler) {
        this.questionHandler = questionHandler;
    }
    @Bean
    public RouterFunction<?> routerFunction(){
        log.info("Starts routing with questionhandler ...");
        return RouterFunctions
                .route(GET("api/question/title").and(accept(MediaType.APPLICATION_JSON)) ,questionHandler::getByTitle)
                .andRoute(DELETE("api/question/{id}").and(accept(MediaType.APPLICATION_JSON)),questionHandler::deleteBy)
                .andRoute(PUT("api/question/{id}").and(accept(MediaType.APPLICATION_JSON)),questionHandler::updateById);

    }
}
