package ru.interview4j.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.Question;

public interface QuestionRepository extends ReactiveCrudRepository<Question, Long> {

    Mono<Question> findBySectionIdAndId(Long sectionId, Long questionId);

}
