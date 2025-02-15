package com.se.tasktracker.mappers;

import com.se.tasktracker.domain.dto.TaskListDto;
import com.se.tasktracker.domain.model.TaskList;

import java.util.List;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
