package ru.interview4j.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.interview4j.domain.User;

import java.util.List;

public interface JwtService {

    String extractUsername(String accessToken);

    boolean validateAccessToken(String accessToken);

    List<SimpleGrantedAuthority> getAuthorities(String accessToken);

    String buildAccessToken(User user);
}
