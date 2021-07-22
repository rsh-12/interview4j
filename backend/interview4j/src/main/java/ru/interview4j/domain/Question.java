package ru.interview4j.domain;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import java.util.Date;


/**
 * Entity class for user's question. It contains
 * id, title of question, body, date of created, and modified.
 * Inner in body will be right answer of question.
 *
 * @author shele
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
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
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @NonNull
    private Long userId;

}
