package com.se.tasktracker.service.impl;

import com.se.tasktracker.domain.model.Task;
import com.se.tasktracker.domain.model.TaskList;
import com.se.tasktracker.domain.model.TaskPriority;
import com.se.tasktracker.domain.model.TaskStatus;


import com.se.tasktracker.repository.TaskListRepository;
import com.se.tasktracker.repository.TaskRepository1;

import com.se.tasktracker.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository1 taskRepository1;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository1 taskRepository1, TaskListRepository taskListRepository) {
        this.taskRepository1 = taskRepository1;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> ListTasks(UUID taskListId) {
       return taskRepository1.findByTaskListId(taskListId);


    }

    @Override
    public Task CreateTask(UUID id ,Task task) {
        if (null != task.getId()){
            throw  new RuntimeException("Task already exists");
        }
        LocalDateTime now = LocalDateTime.now();
        TaskPriority priority =
        Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);
        TaskStatus status = TaskStatus.OPEN;
        TaskList taskList =  taskListRepository.findById(id).orElseThrow( ()-> new RuntimeException("Task not found1"));

        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                status,
                priority,
                now,
                now,
                taskList
        );
        return taskRepository1.save(taskToSave);



    }

    @Override
    public Task UpdateTask(UUID taskId, Task task) {
        Optional<Task> taskOptional = taskRepository1.findById(taskId);
        if (taskOptional.isPresent()) {
           Task taskToUpdate = taskOptional.get();
           taskToUpdate.setTitle(task.getTitle());
           taskToUpdate.setDescription(task.getDescription());
           taskToUpdate.setDueDate(task.getDueDate());
           taskToUpdate.setStatus(task.getStatus());
           taskToUpdate.setPriority(task.getPriority());
           taskToUpdate.setDueDate(task.getDueDate());
           taskToUpdate.setUpdated(LocalDateTime.now());
           return taskRepository1.save(taskToUpdate);
        }
        throw  new RuntimeException("Task not found");
    }

    @Override
    public String DeleteTask(UUID taskId) {
        Optional<Task> task = taskRepository1.findById(taskId);
        if (task.isPresent()) {
            taskRepository1.delete(task.get());
            return "Task deleted successfully";
        }
        throw new RuntimeException("Task not found");
    }

    @Override
    public Optional<Task> getTaskListById(UUID id, UUID taskListId) {
        return taskRepository1.findByTaskListIdAndId(taskListId,id);
    }


}
