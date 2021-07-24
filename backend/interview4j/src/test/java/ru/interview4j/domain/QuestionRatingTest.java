package ru.interview4j.domain;
/*
 * Date: 24.07.2021
 * Time: 9:18 PM
 * */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionRatingTest {

    private QuestionRating rating;

    @BeforeEach
    void setUp() {
        rating = new QuestionRating.QuestionRatingBuilder()
                .setNegativeAnswers(10)
                .setPositiveAnswers(5)
                .setUserId(1L)
                .setQuestionId(1L)
                .build();
    }

    @Test
    public void getters() {
        assertEquals(10, rating.getNegativeAnswers());
        assertEquals(5, rating.getPositiveAnswers());
        assertEquals(1L, rating.getUserId());
        assertEquals(1L, rating.getQuestionId());
    }

    @Test
    public void hashcode_Symmetric_ShouldBeEqual() {
        QuestionRating questionRating = new QuestionRating.QuestionRatingBuilder()
                .setUserId(1L).setQuestionId(1L)
                .setPositiveAnswers(5).setNegativeAnswers(10).build();
        assertTrue(rating.equals(questionRating) && questionRating.equals(rating));
        assertEquals(rating.hashCode(), questionRating.hashCode());
    }
    
}
