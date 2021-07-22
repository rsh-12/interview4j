package ru.interview4j.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.interview4j.domain.User;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {

}
