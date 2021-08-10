package ru.interview4j.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.Question;

public interface QuestionServ {
    Mono<Question>  getTitle(String title);

    Flux<Question> findAllQuestion();

    Mono<Void> removedQuestById(Long id);

    Mono<Question> update (Question q);

}
