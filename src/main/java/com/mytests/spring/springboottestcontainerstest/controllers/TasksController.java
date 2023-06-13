package com.mytests.spring.springboottestcontainerstest.controllers;

import com.mytests.spring.springboottestcontainerstest.data.Task;
import com.mytests.spring.springboottestcontainerstest.repositories.TaskRepository;
import com.mytests.spring.springboottestcontainerstest.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final TaskService taskService;
    private final TaskRepository taskRepository;

    public TasksController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    @PostMapping("/init")
    public String init() {
        taskService.initDB();
        return "tasks DB initialized";
    }

    @GetMapping("/all")
    public List<Task> all() {
        return taskRepository.findAll();

    }

    @GetMapping("/byState/{state}")
    public List<Task> byState(@PathVariable("state") String state) {
        return taskRepository.getTasksByState(state);
    }
}
