package com.mytests.spring.springboottestcontainerstest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
class TestSpringbootTestcontainersTestApplication {

    @Bean
    @ServiceConnection
    MongoDBContainer mongoDbContainer() {
        return new MongoDBContainer("mongo:latest");
    }

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>("postgres:latest")
                .withDatabaseName("test")
                .withUsername("sa")
                .withPassword("sa")
                ;
    }

    public static void main(String[] args) {
        SpringApplication.from(SpringbootTestcontainersTestApplication::main).with(TestSpringbootTestcontainersTestApplication.class).run(args);
    }

}
