package ru.interview4j.domain;
/*
 * Date: 22.07.2021
 * Time: 11:45 PM
 * */

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

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
    private String title;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @NonNull
    private Long userId;

    @Builder(setterPrefix = "set")
    public Section(@NonNull String title, @NonNull Long userId) {
        this.title = title;
        this.userId = userId;
    }

}
