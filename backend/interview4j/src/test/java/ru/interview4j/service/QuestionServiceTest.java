package ru.interview4j.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.interview4j.domain.Question;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionServiceTest {
    @Autowired
    QuestionService questionService;
    @Autowired
    DatabaseClient client;

    @Before
    void  setUp(){
      client.
              sql("insert into question values ('1','Freedom','Nasty weather','1971-07-13','1966-09-13','1','2')");

    }


    @Test
    void testFoundQuestionByTitle(){
        Question question = Question.builder().setTitle("Freedom").setBody("Nasty weather").setSectionId(1l).setUserId(2L).build();
        Mono<Question> flux = questionService.getByTitle("Freedom");
         StepVerifier.create(flux)
                 .expectNextCount(1)
                 .verifyComplete();



    }
}
