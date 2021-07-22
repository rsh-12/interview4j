package ru.interview4j.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.interview4j.domain.Question;

public interface QuestionRepository extends ReactiveCrudRepository<Question, Long> {

}
