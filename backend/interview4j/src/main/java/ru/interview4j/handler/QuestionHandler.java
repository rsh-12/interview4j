package ru.interview4j.handler;
/*
 * Date: 08.08.2021
 * Time: 12:50 PM
 * */

import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class QuestionHandler {

    public @NonNull Mono<ServerResponse> getQuestionById(ServerRequest request) {
        return null;
    }

}
