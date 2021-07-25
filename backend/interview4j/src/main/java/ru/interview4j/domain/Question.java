package ru.interview4j.domain;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


/**
 * Entity class for user's question. It contains
 * id, title of question, body, date of created, and modified.
 * Inner in body will be right answer of question.
 *
 * @author shele
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "title"})
@Table("question")
public class Question {

    @Id
    private Long id;

    @NonNull
    @NotBlank
    private String title;

    @NonNull
    @NotBlank
    private String body;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @NonNull
    private Long sectionId;

    @NonNull
    private Long userId;

    @Builder(setterPrefix = "set")
    private Question(@NonNull String title, @NonNull String body, @NonNull Long sectionId, @NonNull Long userId) {
        this.title = title;
        this.body = body;
        this.sectionId = sectionId;
        this.userId = userId;
    }

}
