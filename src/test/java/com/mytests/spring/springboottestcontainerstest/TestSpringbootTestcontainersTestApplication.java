package com.mytests.spring.springboottestcontainerstest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import static com.mytests.spring.springboottestcontainerstest.TestMongoRepo.mongoDBContainer;

@TestConfiguration(proxyBeanMethods = false)
class TestSpringbootTestcontainersTestApplication {

    @Bean
    @ServiceConnection
    MongoDBContainer mongoDbContainer() {
        return new MongoDBContainer("mongo:latest")
                ;
    }
    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
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
