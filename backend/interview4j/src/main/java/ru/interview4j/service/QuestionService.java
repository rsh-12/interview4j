package ru.interview4j.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.Question;
import ru.interview4j.repository.QuestionRepository;

import javax.naming.NameNotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    // it should be returned question with special title
    public Mono<Question> getByTitle(String title) {
        log.info("check if title for null");
        if (title == null) {
            try {
                throw new NameNotFoundException("not such title " + title);
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }

        }
        return this.questionRepository.findBySpecificTitle(title);
    }
    //Get all question from db with body field will not null
    public Flux<Question> getAll() {
        return  this.questionRepository.findAll();

    }
}