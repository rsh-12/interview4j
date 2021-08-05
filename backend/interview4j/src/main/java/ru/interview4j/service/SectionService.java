package ru.interview4j.service;
/*
 * Date: 05.08.2021
 * Time: 11:30 AM
 * */

import reactor.core.publisher.Mono;
import ru.interview4j.domain.Section;
import ru.interview4j.dto.SectionDto;

public interface SectionService {

    Mono<Section> findSectionById(Long sectionId);

    SectionDto mapToSectionDto(Section section);
}
