package ru.interview4j.domain;
/*
 * Date: 24.07.2021
 * Time: 9:18 PM
 * */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionWithRatingTest {

    private QuestionWithRating rating;

    @BeforeEach
    void setUp() {
        rating = QuestionWithRating.builder()
                .setNegativeAnswers(10)
                .setPositiveAnswers(5)
                .setUserId(1L)
                .setQuestionId(1L)
                .build();
    }

    @Test
    public void hashcode_Symmetric_ShouldBeEqual() {
        QuestionWithRating questionRating = QuestionWithRating.builder()
                .setUserId(1L).setQuestionId(1L)
                .setPositiveAnswers(5).setNegativeAnswers(10).build();
        assertTrue(rating.equals(questionRating) && questionRating.equals(rating));
        assertEquals(rating.hashCode(), questionRating.hashCode());
    }
    
}
