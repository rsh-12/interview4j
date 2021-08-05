package ru.interview4j.router;
/*
 * Date: 05.08.2021
 * Time: 11:41 AM
 * */

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AbstractRouterTestClass {

    protected static final LocalDateTime CREATED_AT_NOW = LocalDateTime.now();
    protected static final LocalDateTime UPDATED_AT_NOW = LocalDateTime.now();
    protected static final String USERNAME = "username";
    protected static final String PASSWORD = "password12345";

    @Autowired
    protected WebTestClient webClient;

}
