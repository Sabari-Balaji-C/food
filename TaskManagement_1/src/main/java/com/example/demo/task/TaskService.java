package com.example.demo.task;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
	
    @Autowired
    private TaskRepository taskRepo;
    
    @Autowired 
    private JdbcTemplate jdbcTemplate;


	public List<Task> getAllTasks() {
		return taskRepo.findAll();
	}
	

	public Task getTaskById(int id) {
		 Optional<Task> task = taskRepo.findById(id);
		 return task.orElse(null);
	}

	public void createTask(Task task) {
		taskRepo.save(task);
		
	}



	public String updateTask(int id,Task task) {
        String updateTaskSql = "UPDATE task SET title = ?, description = ?, due_date = ?, priority = ?, status = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(updateTaskSql, 
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus(),
                task.getId()
            );
        return (rowsAffected > 0) ? "Task updated successfully" : "Task with ID " + task.getId() + " not found";

	}

	public void deleteTask(int id) {
		taskRepo.deleteById(id);
		
	}

}
