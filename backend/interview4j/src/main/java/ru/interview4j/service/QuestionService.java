package ru.interview4j.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.Question;
import ru.interview4j.repository.QuestionRepository;

import javax.naming.NameNotFoundException;

//Created by @shele
@Service
@Slf4j
public class QuestionService {


  private   QuestionRepository questionRepository;

  @Autowired
  public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


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
    //Delete question by id
    public Mono<Void> removeQuestion(Long id){
        return questionRepository.deleteById(id)
                .switchIfEmpty(Mono.empty());
    }
    //Update question by its id
    @Transactional(readOnly = true)
    public Mono<Void> update(Question q){
     return   questionRepository.findById(q.getId())
                .flatMap(questionRepository::save)
                .then();


    }



}