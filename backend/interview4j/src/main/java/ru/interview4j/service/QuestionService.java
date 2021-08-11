package ru.interview4j.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.Question;

public interface QuestionService {

    Mono<Question> findQuestionById(Long questionId);

    Mono<Question> findQuestionBySectionAndId(Long sectionId, Long questionId);

    Flux<Question> findQuestions(long page, long size);

}
