package ru.interview4j.service.impl;
/*
 * Date: 10.08.2021
 * Time: 6:46 AM
 * */

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.Question;
import ru.interview4j.exception.CustomException;
import ru.interview4j.repository.QuestionRepository;
import ru.interview4j.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Mono<Question> findQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .switchIfEmpty(Mono.error(() -> CustomException.notFound("Question not found")));
    }

    @Override
    public Mono<Question> findQuestionBySectionAndId(Long sectionId, Long questionId) {
        return questionRepository.findBySectionIdAndId(sectionId, questionId)
                .switchIfEmpty(Mono.error(() -> CustomException.notFound("Question not found")));
    }

    @Override
    public Flux<Question> findQuestions(long page, long size) {
        return null;
    }
}
