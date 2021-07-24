package ru.interview4j.domain;
/*
 * Date: 24.07.2021
 * Time: 10:46 AM
 * */

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table("role")
public class Role {

    @Id
    private Long id;

    @NonNull
    private ERole name;


}
