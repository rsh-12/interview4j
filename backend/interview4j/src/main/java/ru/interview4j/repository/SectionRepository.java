package ru.interview4j.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.interview4j.domain.Section;

public interface SectionRepository extends ReactiveCrudRepository<Section, Long> {

}
