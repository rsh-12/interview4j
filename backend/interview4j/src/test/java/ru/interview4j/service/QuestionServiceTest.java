package ru.interview4j.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class QuestionServiceTest {

    @Autowired
    QuestionService questionService;
  //  @Autowired
  //  DatabaseClient client;

 //   @Before
  //  void  setUp(){
  //    client.
     //         sql("insert into question values ('1','Freedom','Nasty weather','1971-07-13','1966-09-13','1','2')");

//    }


    @Test
    @DisplayName("test for finding  by title")
    void testFoundQuestionByTitle(){
        StepVerifier.create(questionService.getByTitle("Freedom"))
                .expectSubscription()
                .expectNextMatches(question -> question.getTitle().equals("Freedom"))
                .verifyComplete();



    }
}
