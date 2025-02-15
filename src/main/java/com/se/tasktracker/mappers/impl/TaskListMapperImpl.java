package com.se.tasktracker.mappers.impl;

import com.se.tasktracker.domain.dto.TaskListDto;
import com.se.tasktracker.domain.model.Task;
import com.se.tasktracker.domain.model.TaskList;
import com.se.tasktracker.domain.model.TaskStatus;
import com.se.tasktracker.mappers.TaskListMapper;
import com.se.tasktracker.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {
    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return  new TaskList(
                        taskListDto.id(),
                        taskListDto.title(),
                        taskListDto.description(),
                null,
                null,
                Optional.ofNullable(taskListDto.tasks()).map(task -> task.stream().map(taskMapper::fromDto).toList()).orElse(null)
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
                return new TaskListDto(
                        taskList.getId(),
                        taskList.getTitle(),
                        taskList.getDescription(),
                        Optional.ofNullable(taskList.getTasks()).map(List::size).orElse(0),
                        calculateTaskListProgress(taskList.getTasks()),
                        Optional.ofNullable(taskList.getTasks()).map(tasks -> tasks.stream().map(taskMapper::toDto).toList()).orElse(null)
                );
    }
    private Double calculateTaskListProgress(List<Task> taskList) {
        if (null == taskList || taskList.isEmpty()) {
            return 0.0;
        }

        long closed = taskList.stream().filter(task -> TaskStatus.CLOSE==task.getStatus()).count();
        return (double) closed / taskList.size();
    }
}
