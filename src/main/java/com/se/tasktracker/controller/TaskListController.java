package com.se.tasktracker.controller;

import com.se.tasktracker.domain.dto.TaskListDto;

import com.se.tasktracker.domain.model.TaskList;
import com.se.tasktracker.mappers.TaskListMapper;
import com.se.tasktracker.service.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task-lists")
public class TaskListController {
    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> getTaskLists() {
        return  taskListService.getAllTaskList().stream().map(taskListMapper::toDto).collect(Collectors.toList());
    }
    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskList  cretedTaskList = taskListService.createTaskList(taskListMapper.fromDto(taskListDto));
        return  taskListMapper.toDto(cretedTaskList);
    }
    @GetMapping("/{task_list_id}")
    public Optional<TaskListDto> deleteTaskList(@PathVariable("task_list_id") UUID taskListId) {
        return taskListService.getTaskListById(taskListId).map(taskListMapper::toDto);
    }
    @PutMapping("/{task_list_id}")
    public TaskListDto updateTaskList(@PathVariable("task_list_id") UUID taskListId, @RequestBody TaskListDto taskListDto) {
        return taskListMapper.toDto(taskListService.updateTaskList(taskListId,taskListMapper.fromDto(taskListDto))) ;
    }
    @DeleteMapping("/{task_list_id}")
    public String deleteTaskListById(@PathVariable("task_list_id") UUID taskListId) {
       return taskListService.deleteTaskList(taskListId);
    }

}
