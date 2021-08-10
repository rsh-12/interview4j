package ru.interview4j.dto;

import java.time.LocalDateTime;

public record QuestionDto(String title, String body, LocalDateTime createdAt, LocalDateTime updatedAt) {

}
