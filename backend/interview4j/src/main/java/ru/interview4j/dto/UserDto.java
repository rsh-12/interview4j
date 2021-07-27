package ru.interview4j.dto;
/*
 * Date: 27.07.2021
 * Time: 3:35 PM
 * */

import java.time.LocalDateTime;
import java.util.Set;

public record UserDto(String username, LocalDateTime createdAt, LocalDateTime updatedAt, Set<RoleDto> roles) {

}
