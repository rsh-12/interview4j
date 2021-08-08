package ru.interview4j.dto;

import java.time.LocalDateTime;

public record SectionDto(String title, LocalDateTime createdAt, LocalDateTime updatedAt) {

}
