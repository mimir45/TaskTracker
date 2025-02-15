package com.se.tasktracker.mappers;

import com.se.tasktracker.domain.dto.TaskDto;
import com.se.tasktracker.domain.model.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);
}
