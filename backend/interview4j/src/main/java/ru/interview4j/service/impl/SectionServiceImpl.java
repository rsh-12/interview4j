package ru.interview4j.service.impl;
/*
 * Date: 05.08.2021
 * Time: 11:30 AM
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.Section;
import ru.interview4j.dto.SectionDto;
import ru.interview4j.repository.SectionRepository;
import ru.interview4j.service.SectionService;

import java.util.Comparator;

@Service
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
    public SectionDto mapToSectionDto(Section section) {
        return new SectionDto(section.getTitle(),
                section.getCreatedAt(),
                section.getUpdatedAt());
    }

    @Override
    public Flux<Section> findSections(long page, long size) {
        return sectionRepository.findAll()
                .sort(Comparator.comparing(Section::getCreatedAt))
                .skip(page * size)
                .take(size);
    }

}
