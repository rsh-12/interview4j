package ru.interview4j.domain;
/*
 * Date: 24.07.2021
 * Time: 9:18 PM
 * */

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("question_rating")
public class QuestionRating {

    @Id
    private Long id;

    private int positiveAnswers;

    private int negativeAnswers;

    private Long userId;

    private Long questionId;

    @Builder(setterPrefix = "set")
    private QuestionRating(int positiveAnswers, int negativeAnswers, Long userId, Long questionId) {
        this.positiveAnswers = positiveAnswers;
        this.negativeAnswers = negativeAnswers;
        this.userId = userId;
        this.questionId = questionId;
    }
}

