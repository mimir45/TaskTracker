package com.se.tasktracker.controller;

import com.se.tasktracker.domain.dto.TaskDto;
import com.se.tasktracker.mappers.TaskMapper;
import com.se.tasktracker.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task-lists/{task_list_id}/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDto> getTasks(@PathVariable("task_list_id") UUID task_list_id) {
        return  taskService.ListTasks(task_list_id).stream().map(taskMapper::toDto).collect(Collectors.toList());
    }
    @PostMapping
    public  TaskDto createTask (@PathVariable("task_list_id") UUID task_list_id,TaskDto taskDto) {
        return  taskMapper.toDto( taskService.CreateTask(task_list_id,taskMapper.fromDto(taskDto)));
    }
    @GetMapping("/{id}")
    public Optional<TaskDto> getTask(@PathVariable("task_list_id")UUID task_list_id, @PathVariable("id") UUID id) {
        return  taskService.getTaskListById(id, task_list_id).map(taskMapper::toDto);
    }
    @DeleteMapping("/{id}")
    public String deleteTask( @PathVariable("id") UUID id) {
      return   taskService.DeleteTask(id);
    }
    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable("id") UUID id, @RequestBody TaskDto taskDto) {
        return  taskMapper.toDto(taskService.UpdateTask(id, taskMapper.fromDto(taskDto)));
    }

}
