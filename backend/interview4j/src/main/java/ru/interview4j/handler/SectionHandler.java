package ru.interview4j.handler;
/*
 * Date: 05.08.2021
 * Time: 10:14 AM
 * */

import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SectionHandler {

    public @NonNull Mono<ServerResponse> getSectionById(ServerRequest request) {
        return null;
    }

}
