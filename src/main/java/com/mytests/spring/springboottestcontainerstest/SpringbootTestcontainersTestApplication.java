package com.mytests.spring.springboottestcontainerstest;

import com.mytests.spring.springboottestcontainerstest.services.PersonsService;
import com.mytests.spring.springboottestcontainerstest.services.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootTestcontainersTestApplication implements CommandLineRunner {

    private final PersonsService personsService;
    private final TaskService taskService;

    public SpringbootTestcontainersTestApplication(PersonsService personsService, TaskService taskService) {
        this.personsService = personsService;
        this.taskService = taskService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTestcontainersTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //personsService.initDB();
        //taskService.initDB();
        System.out.println("=== all persons: ===");
        personsService.displayAll();
        personsService.updateStatus();
        System.out.println("=====================");
        System.out.println("=== all persons after update: ===");
        personsService.displayAll();
        System.out.println("=====================");
        System.out.println("=== all tasks: ===");
        taskService.displayAll();
        System.out.println("=====================");
        System.out.println("=== old tasks: ===");
        taskService.displayOldTasks();
        System.out.println("=====================");
    }
}
