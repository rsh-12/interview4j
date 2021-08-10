package ru.interview4j.service;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.interview4j.domain.Question;
import ru.interview4j.repository.QuestionRepository;
import ru.interview4j.service.impl.QuestionServiceImpl;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {

    @InjectMocks
  private QuestionServiceImpl questionService;

    @Mock
   private QuestionRepository repo;

    @Mock
   private QuestionServ questionServ;



    @Test
    @DisplayName("test for finding  by title")
 public   void testFoundQuestionByTitle(){
        Question question = Question.builder().setTitle("Freedom").setBody("Daddy").setUserId(1L).setSectionId(4L).build();
        given(repo.findBySpecificTitle(anyString())).willReturn(Mono.just(question));
        StepVerifier.create(this.questionService.getTitle("Freedom"))
                .expectSubscription()
               .expectNextMatches(q-> question.getTitle().equals("Freedom"))
                .verifyComplete();



    }





}
