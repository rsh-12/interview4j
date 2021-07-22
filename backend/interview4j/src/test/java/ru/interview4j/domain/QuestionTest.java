package ru.interview4j.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


class QuestionTest {

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question();
        question.setTitle("New question");
        question.setBody("Answer of question is");
        question.setCreatedAt(new Date());
        question.setUpdatedAt(new Date());
    }

    @Test
    void getTitle_ShouldBeEqual() {
        assertEquals("New question", question.getTitle());
    }

    @Test
    void getBody_ShouldBeEqual() {
        assertEquals("Answer of question is", question.getBody());
    }

    @Test
    public void getCreatedAt_ShouldReturnCreatedAt() {
        assertEquals(new Date().toString(), question.getCreatedAt().toString());
    }

    @Test
    public void getUpdatedAt_ShouldReturnUpdatedAt() {
        assertEquals(new Date().toString(), question.getUpdatedAt().toString());
    }

    @Test
    public void equals_ShouldBeEqual() {
        assertEquals(question, new Question("New question", "Body", 1L));
    }

    @Test
    public void equals_ShouldNotBeEqual() {
        assertNotEquals(question, new Question("Title", "body", 1L));
    }

    @Test
    public void equals_SameObjects_ShouldBeEqual() {
        assertEquals(question, question);
    }

    @Test
    public void equals_Null_ShouldNotBeEqual() {
        assertFalse(question.equals(null));
    }

    @Test
    public void hashcode_Symmetric_ShouldBeEqual() {
        Question newQuestion = new Question("New question", "Body", 1L);
        assertTrue(question.equals(newQuestion) && newQuestion.equals(question));
        assertEquals(question.hashCode(), newQuestion.hashCode());
    }

    @Test
    public void toString_ShouldBeEqual() {
        String toStr = String.format("Question(id=%s, title=%s, body=%s, createdAt=%s, updatedAt=%s, userId=%s)",
                question.getId(), question.getTitle(), question.getBody(),
                question.getCreatedAt(), question.getUpdatedAt(), question.getUserId());

        assertEquals(toStr, question.toString());
    }

}


