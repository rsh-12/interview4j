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

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"id", "title"})
@Table("section")
public class Section {

    @Id
    private Long id;

    @NonNull
    @NotBlank
    private String title;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @NonNull
    private Long userId;

}
