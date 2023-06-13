package com.mytests.spring.springboottestcontainerstest;

import com.mytests.spring.springboottestcontainerstest.data.Task;
import com.mytests.spring.springboottestcontainerstest.repositories.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.*;

@Testcontainers
@DataMongoTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // deactivate the default behaviour
public class TestMongoRepo {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private TaskRepository repository;

    @Test
    public void checkTasksByDate(){

        Task Task1 = new Task(UUID.randomUUID(), "test task1", new Date(2022, Calendar.DECEMBER, 15),"created");
        Task Task2 = new Task(UUID.randomUUID(), "test task2", new Date(2023, Calendar.MAY, 8),"created");
        Task savedTask1 = repository.save(Task1);
        Task savedTask2 = repository.save(Task2);
        System.out.println("*********** tasks: ******************");
        for (Task task : repository.findAll()) {
            System.out.println(task);
        }
        System.out.println("*************************************");
        Date deadline = new Date(2023, Calendar.JANUARY,1);

        List<Task> Tasks = new ArrayList<>();
        repository.getTasksStartedBeforeDate(deadline).forEach(p -> Tasks.add(p));

        Assertions.assertThat(Tasks).hasSize(1);
        Assertions.assertThat(Tasks.get(0).getName()).isEqualTo("test task1");
    }
}