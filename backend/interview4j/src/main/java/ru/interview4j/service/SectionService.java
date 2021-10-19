package ru.interview4j.service;
/*
 * Date: 05.08.2021
 * Time: 11:30 AM
 * */

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.Section;

public interface SectionService {

    Mono<Section> findOne(Long id);

    Flux<Section> findAll(long page, long size);

    Flux<Section> findAll();

    Mono<Section> save(Section section);

    Mono<Long> countAll();

    Mono<Void> delete(Long id);

}
