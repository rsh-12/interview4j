package ru.interview4j.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class QuestionTest {

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question();
        question.setTitle("New question");
        question.setBody("Answer of question is");
        question.setCreatedAt(LocalDateTime.now());
        question.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    public void getters_ShouldReturnCorrectData() {
        assertEquals("New question", question.getTitle());
        assertEquals("Answer of question is", question.getBody());
//        assertEquals(new Date().toString(), question.getCreatedAt().toString());
//        assertEquals(new Date().toString(), question.getUpdatedAt().toString());
    }

    @Test
    public void equals_ShouldBeEqual() {
        Question question2 = new Question.QuestionBuilder()
                .setTitle("New question").setBody("Body")
                .setSectionId(1L).setUserId(1L)
                .build();
        assertEquals(question, question2);
    }

    @Test
    public void equals_ShouldNotBeEqual() {
        Question question2 = new Question.QuestionBuilder()
                .setTitle("Title").setBody("Body")
                .setSectionId(1L).setUserId(1L)
                .build();
        assertNotEquals(question, question2);
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
        Question newQuestion = new Question.QuestionBuilder()
                .setTitle("New question").setBody("Body")
                .setSectionId(1L).setUserId(1L)
                .build();
        assertTrue(question.equals(newQuestion) && newQuestion.equals(question));
        assertEquals(question.hashCode(), newQuestion.hashCode());
    }

//    @Test
//    public void toString_ShouldBeEqual() {
//        String toStr = String
//                .format("Question(id=%s, title=%s, body=%s, createdAt=%s, updatedAt=%s, sectionId=%s, userId=%s)",
//                        question.getId(), question.getTitle(), question.getBody(), question.getCreatedAt(),
//                        question.getUpdatedAt(), question.getSectionId(), question.getUserId());
//
//        assertEquals(toStr, question.toString());
//    }

}


