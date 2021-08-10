package ru.interview4j.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.Question;
import ru.interview4j.repository.QuestionRepository;
import ru.interview4j.service.QuestionServ;

import javax.naming.NameNotFoundException;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionServ {

    private QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Mono<Question> getTitle(String title) {
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


    @Override
    public Flux<Question> findAllQuestion() {
        return  this.questionRepository.findAll()
                .filter(question -> question.getId()!=null)
                .log();
    }

    @Override
    public Mono<Void> removedQuestById(Long id) {
        return questionRepository.deleteById(id)
                .switchIfEmpty(Mono.empty());
    }

    @Transactional(readOnly = true)
    @Override
    public Mono<Question> update(Question q) {
        return questionRepository.findById(q.getId())
                .log("finding question with item id")
                .map(question -> {
                    question.setBody(q.getBody());
                    question.setTitle(q.getTitle());
                    question.setCreatedAt(q.getCreatedAt());
                    return question;
                })
                .flatMap(questionRepository::save)
                .log("Updated question is over");
    }
}
