package com.se.tasktracker.domain.dto;

import com.se.tasktracker.domain.model.TaskPriority;
import com.se.tasktracker.domain.model.TaskStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status

) {



    public TaskDto(UUID id, String title, String description, LocalDateTime dueDate, TaskPriority priority, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }
}
