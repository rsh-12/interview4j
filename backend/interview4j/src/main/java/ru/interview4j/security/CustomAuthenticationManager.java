package ru.interview4j.security;
/*
 * Date: 28.07.2021
 * Time: 11:16 AM
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.interview4j.service.JwtService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtService jwtService;

    @Autowired
    public CustomAuthenticationManager(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String accessToken = authentication.getCredentials().toString();
        String username;

        try {
            username = jwtService.extractUsername(accessToken);
        } catch (Exception e) {
            username = null;
            e.printStackTrace();
        }

        if (username != null && jwtService.validateAccessToken(accessToken)) {
            List<SimpleGrantedAuthority> authorities = jwtService.getRoles(accessToken).stream()
                    .map(roleDto -> new SimpleGrantedAuthority(roleDto.authority().name()))
                    .collect(Collectors.toList());

            var auth = new UsernamePasswordAuthenticationToken(username, "", authorities);
            return Mono.just(auth);
        }

        return Mono.empty();
    }


}
