package ru.interview4j.service;

import ru.interview4j.domain.User;
import ru.interview4j.dto.RoleDto;

import java.util.List;
import java.util.Set;

public interface JwtService {

    String extractUsername(String accessToken);

    boolean validateAccessToken(String accessToken);

    Set<RoleDto> getRoles(String accessToken);

    String buildAccessToken(User user);
}
