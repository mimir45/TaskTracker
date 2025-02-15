package com.se.tasktracker.service;
import com.se.tasktracker.domain.model.Task;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<Task> ListTasks(UUID taskListId);
    Task CreateTask(UUID id, Task task);
    Task UpdateTask(UUID taskId, Task task);
    String DeleteTask(UUID taskId);
    Optional<Task> getTaskListById(UUID id, UUID taskListId);
}
