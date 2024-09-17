package com.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

	@Autowired
	private TaskService service;
	
	@GetMapping(value="/showTasks")
	public List<Tasks> showTasks() {
	    return service.showAll();
	}
	
	@GetMapping(value="/searchTask/{id}")
	public Tasks searchTask(@PathVariable int id) {
		return service.searchTask(id);
	}
	
	@PostMapping(value="/addTask")
	public void addTask(@RequestBody Tasks tasks) {
		service.addTask(tasks);
	}
	
	@DeleteMapping(value="/deleteTask/{id}")
	public void deleteTask(@PathVariable int id) {
		service.deleteTask(id);
	}
	
	@PutMapping(value="/updateTask")
	public void updateTask(@RequestBody Tasks tasks) {
		service.updateTask(tasks);
	}
}
