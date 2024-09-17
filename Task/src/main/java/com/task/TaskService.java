package com.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	@Autowired
	private TaskManagementRepository repo;
	
	public List<Tasks> showAll() {
		return repo.findAll();
	}
	
	public Tasks searchTask(int taskId) {
		return repo.findById(taskId).get();
	}
	
	public void addTask(Tasks tasks) {
		repo.save(tasks);
	}
	
	public void updateTask(Tasks tasks) {
		repo.save(tasks);
	}
	
	public void deleteTask(int id) {
		repo.deleteById(id);
	}
}
