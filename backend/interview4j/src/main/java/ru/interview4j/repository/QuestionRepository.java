package ru.interview4j.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.Question;

public interface QuestionRepository extends ReactiveCrudRepository<Question, Long> {

    @Query(value = "select title from question where title =1$")
    Mono<Question> findBySpecificTitle(@Param("title")String title);
}
