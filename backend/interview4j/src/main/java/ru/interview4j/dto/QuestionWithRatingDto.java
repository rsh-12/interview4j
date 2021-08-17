package ru.interview4j.dto;
/*
 * Date: 24.07.2021
 * Time: 9:18 PM
 * */

public record QuestionWithRatingDto(Long id, int positiveAnswers, int negativeAnswers,
                                    Long userId, Long questionId) {

}

