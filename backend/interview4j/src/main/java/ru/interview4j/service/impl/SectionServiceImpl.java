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
    @Transactional(readOnly = true)
    public Mono<Section> findOne(Long id) {
        log.debug("Request to get Section: {}", id);
        return sectionRepository.findById(id);
    }

    @Override
    public Flux<Section> findAll(long page, long size) {
        log.debug("Request to get all Sections as a page");
        return sectionRepository.findAll()
                .sort(Comparator.comparing(Section::getCreatedAt))
                .skip(page * size)
                .take(size);
    }

    @Override
    public Flux<Section> findAll() {
        log.debug("Request to get all Sections");
        return sectionRepository.findAll();
    }

    @Override
    public Mono<Section> save(Section section) {
        log.debug("Request to save Section: {}", section);
        return sectionRepository.save(section);
    }

    @Override
    public Mono<Long> countAll() {
        return sectionRepository.count();
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Section: {}", id);
        return sectionRepository.deleteById(id);
    }

}
