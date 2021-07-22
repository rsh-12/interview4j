package ru.interview4j.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class QuestionsTest {

    private Questions questions;

    @BeforeEach
    void checkUp(){
        questions= new Questions();
        questions.setTitle("New question");
        questions.setBody("Answer of question is");
     //  questions.setCreatedAt(new Date(String.valueOf(new SimpleDateFormat("20.02.2014"))));
        //questions.setModifiedAt(new Date(String.valueOf(new SimpleDateFormat("20.03.2014"))));


    }

    @Test
    void itemQuestionsAssesShouldWork() {
        assertEquals("New question", questions.getTitle());
        assertEquals("Answer of question is", questions.getBody());
       // assertEquals(new Date().toString(), questions.getCreatedAt().toString());
        //assertEquals(new Date().toString(), questions.getModifiedAt().toString());
    }
    @Test
    void itemShouldNotReturnEquals(){
    //  LocalDate zone = LocalDate.parse("2018-12-14");
     // LocalDate zone2 = LocalDate.parse("2019-07-11");
        Questions qest = new Questions("New question","Answer of question is ");
          //  qest.setId(2l);
        qest.setTitle("first");
           qest.setBody("Todo rest");
         //  qest.setCreatedAt(new Date("23.06.2019"));
         //  qest.setModifiedAt(new Date("12.08.2020"));
            assertNotEquals(questions,qest);
        }

 @Test
   public void hashcode_mustBeEqual() {
      Questions q = new Questions("New question","Answer of question is");
       assertEquals(questions.hashCode(),q.hashCode());

    }





    }


