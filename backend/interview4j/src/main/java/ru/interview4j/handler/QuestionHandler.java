package ru.interview4j.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.Question;
import ru.interview4j.service.impl.QuestionServiceImpl;

import javax.validation.constraints.NotNull;

@Component
@Slf4j
public class QuestionHandler {

    private   QuestionServiceImpl questionService;

    @Autowired
    public QuestionHandler(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

    public Mono<ServerResponse> getByTitle(ServerRequest req){
        String val = String.valueOf(req.pathVariable("title"));

        Mono<Question> questionMono = questionService.getTitle(val);
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

    public Mono<ServerResponse> deleteBy(ServerRequest req){
        Question question= Question.builder().build();

        return questionService.removedQuestById(question.getId())
                .flatMap(r-> ServerResponse.noContent().build());

    }





}
