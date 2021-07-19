package ru.interview4j.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.interview4j.domain.User;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

}
