package com.mytests.spring.springboottestcontainerstest;

import com.mytests.spring.springboottestcontainerstest.services.PersonsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootTestcontainersTestApplication implements CommandLineRunner {

    private final PersonsService personsService;

    public SpringbootTestcontainersTestApplication(PersonsService personsService) {
        this.personsService = personsService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTestcontainersTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        personsService.initDB();
        personsService.displayAll();
        personsService.updateStatus();
        personsService.displayAll();
    }
}
