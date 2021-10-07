package ru.interview4j.rest.handler;
/*
 * Date: 08.08.2021
 * Time: 12:50 PM
 * */

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Component
public class QuestionHandler {

    private final QuestionService questionService;

    @Autowired
    public QuestionHandler(QuestionService questionService) {
        this.questionService = questionService;
    }

    public @NonNull Mono<ServerResponse> getQuestionById(ServerRequest request) {
        Long sectionId = Long.valueOf(request.pathVariable("sectionId"));
        Long questionId = Long.valueOf(request.pathVariable("id"));
        log.debug("sectionId={}, questionId={}", sectionId, questionId);

        Mono<QuestionDto> question = questionService.findQuestionBySectionAndId(sectionId, questionId)
                .map(CustomMapper::mapToDto);

        return question.flatMap(questionDto -> ok()
                .contentType(APPLICATION_JSON)
                .body(fromValue(questionDto)));
    }

}
