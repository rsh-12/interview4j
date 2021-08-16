package ru.interview4j.util;

import ru.interview4j.domain.Question;
import ru.interview4j.domain.Section;
import ru.interview4j.domain.User;
import ru.interview4j.dto.QuestionDto;
import ru.interview4j.dto.SectionDto;
import ru.interview4j.dto.UserDto;

import java.util.function.Function;

public record CustomMapper() {

    public static SectionDto mapToDto(Section section) {
        return new SectionDto(section.getTitle(),
                section.getCreatedAt(),
                section.getUpdatedAt());
    }

    public static UserDto mapToDto(User user) {
        return new UserDto(user.getUsername(), user.getCreatedAt(), user.getUpdatedAt());
    }

    public static QuestionDto mapToDto(Question question) {
        return new QuestionDto(question.getTitle(),
                question.getBody(),
                question.getCreatedAt(),
                question.getUpdatedAt());
    }

    /* Mapping with custom logic */
    <T, R> R mapToAny(T t, Function<T, R> f) {
        return f.apply(t);
    }

}
