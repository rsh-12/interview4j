package ru.interview4j.handler;
/*
 * Date: 08.08.2021
 * Time: 12:50 PM
 * */

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.interview4j.dto.QuestionDto;
import ru.interview4j.service.QuestionService;
import ru.interview4j.util.CustomMapper;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class QuestionHandler {

    private final QuestionService questionService;

    @Autowired
    public QuestionHandler(QuestionService questionService) {
        this.questionService = questionService;
    }

    public @NonNull Mono<ServerResponse> getQuestionById(ServerRequest request) {
        Long questionId = Long.valueOf(request.pathVariable("id"));
        Mono<QuestionDto> question = questionService.findQuestionById(questionId)
                .map(CustomMapper::mapToDto);

        return question.flatMap(questionDto -> ok()
                .contentType(APPLICATION_JSON)
                .body(fromValue(questionDto)));
    }

}
