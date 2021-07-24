package ru.interview4j.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.interview4j.domain.Role;

public interface RoleRepository extends ReactiveCrudRepository<Role, Long> {
}
