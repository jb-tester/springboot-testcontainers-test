package com.mytests.spring.springboottestcontainerstest.services;

import com.mytests.spring.springboottestcontainerstest.data.Task;
import com.mytests.spring.springboottestcontainerstest.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


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

}
