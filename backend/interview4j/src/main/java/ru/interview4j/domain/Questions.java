package ru.interview4j.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * Entity class for user's question. It contains
 * id , title of question , body , date of created , and modified.
 * Inner in body will be right answer of question.
 *
 * @author  shele
 *
 *
 *
 */
@Data
@NoArgsConstructor
@Table("question")
public class Questions {
    public Questions(String title, String body) {
        this.title = title;
        this.body = body;
    }
    @Id
    private Long id;
    @NotBlank
    @Column
    @NotNull
    private String title;
    @NotBlank
    @Column
    @NotNull
    private String body;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private  Date modifiedAt;


    //private User user;
}
