package com.mytests.spring.springboottestcontainerstest.services;

import com.mytests.spring.springboottestcontainerstest.data.Task;
import com.mytests.spring.springboottestcontainerstest.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * *
 * <p>Created by irina on 6/13/23.</p>
 * <p>Project: springboot-testcontainers-test</p>
 * *
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void initDB(){
        taskRepository.save(new Task(UUID.randomUUID(),"task1",new Date(2022, 6,12), "created"));
        taskRepository.save(new Task(UUID.randomUUID(),"task2",new Date(2022, 11,20), "created"));
        taskRepository.save(new Task(UUID.randomUUID(),"task3",new Date(2023, 5,21), "created"));
        taskRepository.save(new Task(UUID.randomUUID(),"task3",new Date(), "created"));
    }
    public void displayAll(){
        for (Task task : taskRepository.findAll()) {
            System.out.println(task);
        }
    }
    public void displayOldTasks(){
        for (Task task : taskRepository.getTasksStartedBeforeDate(new Date(2023, 0, 1))) {
            System.out.println(task);
        }
    }
}
