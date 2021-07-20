package ru.interview4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@EnableReactiveMongoAuditing
@SpringBootApplication
public class Interview4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(Interview4jApplication.class, args);
    }

}
