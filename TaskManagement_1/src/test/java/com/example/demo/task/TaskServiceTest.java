package com.example.demo.task;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepo;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTasks() {
        // Given
        Task task1 = new Task(1, "Title1", "Description1", Date.valueOf("2024-09-17"), "High", "Pending");
        Task task2 = new Task(2, "Title2", "Description2", Date.valueOf("2024-09-18"), "Medium", "Completed");
        when(taskRepo.findAll()).thenReturn(Arrays.asList(task1, task2));

        // When
        List<Task> tasks = taskService.getAllTasks();

        // Then
        assertNotNull(tasks);
        assertEquals(2, tasks.size());
        assertEquals("Title1", tasks.get(0).getTitle());
    }

    @Test
    void testGetTaskById() {
        // Given
        Task task = new Task(1, "Title1", "Description1", Date.valueOf("2024-09-17"), "High", "Pending");
        when(taskRepo.findById(anyInt())).thenReturn(Optional.of(task));

        // When
        Task foundTask = taskService.getTaskById(1);

        // Then
        assertNotNull(foundTask);
        assertEquals("Title1", foundTask.getTitle());
    }

    @Test
    void testGetTaskByIdNotFound() {
        // Given
        when(taskRepo.findById(anyInt())).thenReturn(Optional.empty());

        // When
        Task foundTask = taskService.getTaskById(1);

        // Then
        assertNull(foundTask);
    }

    @Test
    void testCreateTask() {
        // Given
        Task task = new Task(1, "Title1", "Description1", Date.valueOf("2024-09-17"), "High", "Pending");

        // When
        taskService.createTask(task);

        // Then
        verify(taskRepo, times(1)).save(task);
    }

    @Test
    void testUpdateTask() {
        // Given
        Task task = new Task(1, "Title1", "Description1", Date.valueOf("2024-09-17"), "High", "Pending");
        String updateTaskSql = "UPDATE task SET title = ?, description = ?, due_date = ?, priority = ?, status = ? WHERE id = ?";
        when(jdbcTemplate.update(updateTaskSql, task.getTitle(), task.getDescription(), task.getDueDate(), task.getPriority(), task.getStatus(), task.getId())).thenReturn(1);

        // When
        String result = taskService.updateTask(1, task);

        // Then
        assertEquals("Task updated successfully", result);
    }

    @Test
    void testUpdateTaskNotFound() {
        // Given
        Task task = new Task(1, "Title1", "Description1", Date.valueOf("2024-09-17"), "High", "Pending");
        String updateTaskSql = "UPDATE task SET title = ?, description = ?, due_date = ?, priority = ?, status = ? WHERE id = ?";
        when(jdbcTemplate.update(updateTaskSql, task.getTitle(), task.getDescription(), task.getDueDate(), task.getPriority(), task.getStatus(), task.getId())).thenReturn(0);

        // When
        String result = taskService.updateTask(1, task);

        // Then
        assertEquals("Task with ID 1 not found", result);
    }

    @Test
    void testDeleteTask() {
        // When
        taskService.deleteTask(1);

        // Then
        verify(taskRepo, times(1)).deleteById(1);
    }
}
