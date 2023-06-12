package com.mytests.spring.springboottestcontainerstest;

import com.mytests.spring.springboottestcontainerstest.data.Person;
import com.mytests.spring.springboottestcontainerstest.repositories.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * *
 * <p>Created by irina on 6/12/2023.</p>
 * <p>Project: springboot-testcontainers-test</p>
 * *
 */
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // deactivate the default behaviour
@DataJpaTest
public class TestPostgresRepo {

    @Container
    static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres:latest")
            .withDatabaseName("test")
            .withUsername("sa")
            .withPassword("sa");
    private final PersonRepository personRepository;

    public TestPostgresRepo(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresqlContainer::getUsername);
        registry.add("spring.datasource.password", postgresqlContainer::getPassword);
    }

    @Test
    void testPersons() {
        personRepository.save(new Person(1,"vasya","pupkin",25,"default"));
        personRepository.save(new Person(2,"vanya","petrov",15,"default"));
        personRepository.save(new Person(3,"valya","sidorov",15,"default"));
        personRepository.save(new Person(4,"petya","ivanov",30,"default"));
        personRepository.save(new Person(5,"pasha","pavlov",33,"default"));
        assertEquals(1, personRepository.findBySurname("ivanov").size());
        personRepository.updateStatus("adult",18);
        assertEquals(3, personRepository.getByStatus("adult").size());
    }
}
