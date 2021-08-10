package ru.interview4j.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.test.StepVerifier;
import ru.interview4j.domain.Question;
import ru.interview4j.repository.QuestionRepository;
import ru.interview4j.service.impl.QuestionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {

    @InjectMocks
  private QuestionServiceImpl questionService;

    @MockBean
   private QuestionRepository repo;

   @Before
  public void setUp(){
       Question question = Question.builder().setTitle("Freedom").setBody("Phill Morris").setSectionId(2L).setUserId(4L).build();
      this.repo.save(question);
   }


    @Test
    @DisplayName("test for finding  by title")
 public   void testFoundQuestionByTitle(){
        StepVerifier.create(this.questionService.getTitle("Freedom"))
                .expectSubscription()
               // .expectNextMatches(question -> question.getTitle().equals("Freedom"))
                .verifyComplete();



    }


}
