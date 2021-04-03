package com.rakib.usermodule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@SpringBootApplication
@EnableReactiveMongoRepositories("com.rakib.usermodule.repository")
public class UserModuleApplication {
    public static void main(String[] args) throws URISyntaxException {
        SpringApplication.run(UserModuleApplication.class, args);
        log.info(String.valueOf(new URI("http://localhost:9090/swagger-ui/")));
    }
}
