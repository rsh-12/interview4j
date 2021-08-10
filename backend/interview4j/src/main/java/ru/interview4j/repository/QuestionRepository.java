package ru.interview4j.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.Question;

@Repository
public interface QuestionRepository extends ReactiveCrudRepository<Question, Long> {

    @Query(value = "select title from question where title =:title")
    Mono<Question> findBySpecificTitle(String title);
}
