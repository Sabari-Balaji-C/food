package com.example.demo.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/AllTask")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/TaskById/{id}")
    public Task getTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/CreateTask")
    public void createTask(@RequestBody Task task) {
       taskService.createTask(task);
    }
 
    @PutMapping("/UpdateTask/{id}")
    public String updateTask(@PathVariable int id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("DeleteTask/{id}")
    public void deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
    }
}