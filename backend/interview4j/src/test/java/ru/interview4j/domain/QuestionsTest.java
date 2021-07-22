package ru.interview4j.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class QuestionsTest {

    private Questions questions;

    @BeforeEach
    void checkUp(){
        questions= new Questions();
        questions.setTitle("New question");
        questions.setBody("Answer of question is");
        questions.setCreatedAt(LocalDate.parse("2021-07-22"));
        questions.setModifiedAt(LocalDate.parse("2021-07-23"));


    }

    @Test
    void itemQuestionsAssesShouldWork() {
        assertEquals("New question", questions.getTitle());
        assertEquals("Answer of question is", questions.getBody());
        assertEquals(new Date().toString(), questions.getCreatedAt().toString());
        assertEquals(new Date().toString(), questions.getModifiedAt().toString());
    }
    @Test
    void itemShouldNotReturnEquals(){
    //  LocalDate zone = LocalDate.parse("2018-12-14");
     // LocalDate zone2 = LocalDate.parse("2019-07-11");
        Questions qest = new Questions(null,"New question","Answer of question is",LocalDate.parse("2021-07-22"),LocalDate.parse("2021-07-23"));
          //  qest.setId(2l);
        qest.setTitle("first");
           qest.setBody("Todo rest");
           qest.setCreatedAt(LocalDate.parse("2016-12-23"));
           qest.setModifiedAt(LocalDate.parse("2015-07-12"));
            assertNotEquals(questions,qest);
        }

    @Test
    public void hashcode_mustBeEqual() {
       Questions q = new Questions(null,"New question","Answer of question is",LocalDate.parse("2021-07-22"),LocalDate.parse("2021-07-23"));
       assertEquals(questions.hashCode(),q.hashCode());

    }





    }


