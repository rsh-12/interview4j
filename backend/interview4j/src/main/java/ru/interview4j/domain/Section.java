package ru.interview4j.domain;
/*
 * Date: 22.07.2021
 * Time: 11:45 PM
 * */

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * This class is associated with the {@link User} and {@link Question} classes.
 *
 * @author rsh12
 * @see User
 * @see Question
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "title"})
@Table("section")
public class Section {

    @Id
    private Long id;

    @NonNull
    @NotBlank
    private String title;

    @CreatedDate
    private Date createdAt = new Date();

    @LastModifiedDate
    private Date updatedAt = new Date();

    @NonNull
    private Long userId;

    @Builder(setterPrefix = "set")
    public Section(@NonNull String title, @NonNull Long userId) {
        this.title = title;
        this.userId = userId;
    }

}
