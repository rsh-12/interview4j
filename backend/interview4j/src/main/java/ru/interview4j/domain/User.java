package ru.interview4j.domain;
/*
 * Date: 17.07.2021
 * Time: 8:34 AM
 * */

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


/**
 * The main entity of the app, used for authentication/authorization,
 * creating sections and questions.
 *
 * @author rsh-12
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "username"})
@Table("usr")
public class User {

    @Id
    private Long id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @JsonIgnore
    @NotBlank
    @Size(min = 8, max = 128)
    private String password;

    @CreatedDate
    private Date createdAt = new Date();

    @LastModifiedDate
    private Date updatedAt = new Date();

    @Builder(setterPrefix = "set")
    public User(@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }

}
