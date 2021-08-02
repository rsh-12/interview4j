package ru.interview4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import reactor.core.publisher.Mono;
import ru.interview4j.domain.ERole;
import ru.interview4j.domain.Role;
import ru.interview4j.repository.RoleRepository;

@EnableR2dbcAuditing
@SpringBootApplication
public class Interview4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(Interview4jApplication.class, args);
    }

    @Autowired
    private RoleRepository repository;

    @Bean
    public CommandLineRunner clr() {
        return args -> repository.existsByName(ERole.ROLE_USER)
                .flatMap(ROLE_USER_EXIST -> !ROLE_USER_EXIST
                        ? repository.save(new Role(ERole.ROLE_USER))
                        : Mono.empty()).subscribe();
    }

}
