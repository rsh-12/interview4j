package ru.interview4j.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.Question;
import ru.interview4j.service.QuestionService;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
@Slf4j
public class QuestionHandler {

    @Autowired
    private QuestionService questionService;

    public Mono<ServerResponse> getByTitle(ServerRequest req){
        String val = String.valueOf(req.pathVariable("title"));

        Mono<Question> questionMono = questionService.getByTitle(val);
            return questionMono
                    .flatMap(question -> ServerResponse
                            .ok().contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(question))
                    .switchIfEmpty(Mono.error(()-> new NoSuchFieldException("not title")));



    }
    @NotNull
    public Mono<ServerResponse> updateById(ServerRequest serverRequest){
        return ServerResponse.status(HttpStatus.NO_CONTENT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(serverRequest.bodyToMono(Question.class).flatMap(questionService::update),Question.class);
    }



}
