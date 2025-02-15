package com.se.tasktracker.service.impl;

import com.se.tasktracker.domain.model.TaskList;
import com.se.tasktracker.repository.TaskListRepository;
import com.se.tasktracker.service.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {
    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> getAllTaskList() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {

       if(null!=taskList.getId()) {
           throw new IllegalArgumentException("Task list already exists");
       }
       if (null == taskList.getTitle()||taskList.getTitle().isEmpty()) {
           throw new IllegalArgumentException("Task list title cannot be empty");
       }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                now,
                now,
                null
        ));
    }

    @Override
    public Optional<TaskList> getTaskListById(UUID id) {
        Optional<TaskList> taskList = taskListRepository.findById(id);
        if(taskList.isPresent()) {
            return taskList;
        }
        throw new IllegalArgumentException("Task list does not exist");
    }

    @Override
    public TaskList updateTaskList(UUID id, TaskList taskList) {
        Optional<TaskList> taskListOptional = taskListRepository.findById(id);
        if(taskListOptional.isPresent()) {
            TaskList oldTaskList = taskListOptional.get();
            oldTaskList.setTitle(taskList.getTitle());
            oldTaskList.setDescription(taskList.getDescription());
            oldTaskList.setUpdatedAt(LocalDateTime.now());
            return taskListRepository.save(oldTaskList);
        }
        throw new IllegalArgumentException("Task list does not exist");
    }

    @Override
    public String deleteTaskList(UUID id) {
        Optional<TaskList> taskListOptional = taskListRepository.findById(id);
        if(taskListOptional.isPresent()) {
            taskListRepository.delete(taskListOptional.get());
            return "deleted";
        }
        throw new IllegalArgumentException("Task list does not exist");
    }
}
