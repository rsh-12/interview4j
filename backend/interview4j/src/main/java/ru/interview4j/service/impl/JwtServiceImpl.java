package ru.interview4j.service.impl;
/*
 * Date: 28.07.2021
 * Time: 11:28 AM
 * */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.interview4j.domain.ERole;
import ru.interview4j.domain.User;
import ru.interview4j.dto.RoleDto;
import ru.interview4j.service.JwtService;

import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expirationTime;

    @Override
    public String extractUsername(String accessToken) {
        return getClaimsFromJwt(accessToken).getSubject();
    }

    @Override
    public boolean validateAccessToken(String accessToken) {
        return getClaimsFromJwt(accessToken)
                .getExpiration().toInstant().isAfter(Instant.now());
    }

    @Override
    public Set<RoleDto> getRoles(String accessToken) {
        return extractRoles(getClaimsFromJwt(accessToken));
    }

    @Override
    public String buildAccessToken(User user) {
        long expiration = Long.parseLong(expirationTime);
        Date creationDate = new Date();
        Date expirationDate = new Date(creationDate.getTime() + expiration * 1000);

        Set<RoleDto> roles = user.getRoles().stream()
                .map(role -> new RoleDto(role.getName()))
                .collect(Collectors.toSet());

        return Jwts.builder()
                .setClaims(Map.of("roles", roles))
                .setSubject(user.getUsername())
                .setIssuedAt(creationDate)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    private Claims getClaimsFromJwt(String accessToken) {
        String key = Base64.getEncoder().encodeToString(secret.getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
    }

    private Set<RoleDto> extractRoles(Claims claims) {
        String rolesClaims = (String) claims.get("roles");
        Set<RoleDto> userRoles;

        if (rolesClaims.contains(",")) {
            String[] roles = rolesClaims.split(",");
            userRoles = Arrays.stream(roles)
                    .map(role -> new RoleDto(ERole.valueOf(role)))
                    .collect(Collectors.toSet());
        } else {
            userRoles = Collections.singleton(new RoleDto(ERole.valueOf(rolesClaims)));
        }

        return userRoles;
    }
}
