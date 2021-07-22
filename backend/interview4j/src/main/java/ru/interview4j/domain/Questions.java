package ru.interview4j.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


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
@AllArgsConstructor
@NoArgsConstructor
@Table("question")
public class Questions {

    @Id
    private Long id;
    @JsonIgnore
    @NotBlank
    @Column
    private String title;
    @JsonIgnore
    @NotNull
    @NotBlank
    @Column
    private String body;

    @CreatedDate
    private LocalDate createdAt;
    
    @LastModifiedDate
    private LocalDate modifiedAt;


    //private User user;
}
