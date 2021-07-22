package ru.interview4j.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class QuestionsTest {

    private Question question;

    @BeforeEach
    void checkUp() {
        question = new Question();
        question.setTitle("New question");
        question.setBody("Answer of question is");

    }

    @Test
    void itemQuestionsAssesShouldWork() {
        assertEquals("New question", question.getTitle());
        assertEquals("Answer of question is", question.getBody());
    }

    @Test
    void itemShouldNotReturnEquals() {
        Question ques = new Question("New question", "Answer of question is");
        ques.setId(1L);
        ques.setBody("first");
        ques.setTitle("not idle");
        assertNotEquals(question,ques);
    }

    @Test
    public void hashcode_mustBeEqual() {
        Question ques = new Question("New question", "Answer of question is");
        assertEquals(question.hashCode(),ques.hashCode());
    }

}


