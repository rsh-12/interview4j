package ru.interview4j.handler;
/*
 * Date: 05.08.2021
 * Time: 10:14 AM
 * */

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.Section;
import ru.interview4j.dto.SectionDto;
import ru.interview4j.exception.CustomException;
import ru.interview4j.service.SectionService;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class SectionHandler {

    private final SectionService sectionService;

    @Autowired
    public SectionHandler(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    public @NonNull Mono<ServerResponse> getSectionById(ServerRequest request) {
        Long sectionId = Long.valueOf(request.pathVariable("id"));
        Mono<Section> section = sectionService.findSectionById(sectionId)
                .switchIfEmpty(Mono.error(() -> CustomException.notFound("Section not found")));

        return section.map(sectionService::mapToSectionDto)
                .flatMap(sectionDto -> ok().contentType(APPLICATION_JSON)
                        .body(fromValue(sectionDto)));
    }

    public @NonNull Mono<ServerResponse> getSections(ServerRequest request) {
        String page = request.queryParam("page").orElse("0");
        String size = request.queryParam("size").orElse("20");

        Flux<SectionDto> sectionsFlux = sectionService.findSections(Long.parseLong(page), Long.parseLong(size))
                .switchIfEmpty(Mono.error(() -> CustomException.notFound("No sections found")))
                .map(sectionService::mapToSectionDto);

        return ok().contentType(APPLICATION_JSON)
                .body(sectionsFlux, SectionDto.class);
    }

}
