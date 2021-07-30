package ru.interview4j.security;
/*
 * Date: 28.07.2021
 * Time: 11:16 AM
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CustomSecurityContextRepository implements ServerSecurityContextRepository {

    private final CustomAuthenticationManager authenticationManager;

    @Autowired
    public CustomSecurityContextRepository(CustomAuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Mono<Void> save(ServerWebExchange serverWebExchange, SecurityContext securityContext) {
        throw new IllegalStateException("Save method not supported");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String accessToken = authHeader.substring(7);
            var auth = new UsernamePasswordAuthenticationToken(accessToken, accessToken);
            return authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
        }
        return Mono.empty();
    }

}


