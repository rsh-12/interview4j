package ru.interview4j.service.impl;
/*
 * Date: 05.08.2021
 * Time: 11:30 AM
 * */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.Section;
import ru.interview4j.exception.CustomException;
import ru.interview4j.repository.SectionRepository;
import ru.interview4j.service.SectionService;

import java.util.Comparator;

@Slf4j
@Service
@Transactional
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    @Autowired
    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public Mono<Section> findSectionById(Long sectionId) {
        return sectionRepository.findById(sectionId);
    }

    @Override
    public Flux<Section> findSections(long page, long size) {
        return sectionRepository.findAll()
                .sort(Comparator.comparing(Section::getCreatedAt))
                .skip(page * size)
                .take(size)
                .switchIfEmpty(Mono.error(() -> CustomException.notFound("No sections found")));
    }

    @Override
    public Mono<Section> save(Section section) {
        log.debug("Request to save Section: {}", section);
        return sectionRepository.save(section);
    }

}
