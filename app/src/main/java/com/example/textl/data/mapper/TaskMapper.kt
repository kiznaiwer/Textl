package com.example.textl.data.mapper

import com.example.textl.data.dto.TaskDto
import com.example.textl.domain.model.Task


    fun TaskDto.toDomain(id: String): Task {
        return Task(
            id = id,
            title = title,
            description = description,
            isCompleted = completed,
            createdAt = createdAt,
            userId = userId
        )
    }

    fun Task.toDto(): TaskDto {
        return TaskDto(
            title = title,
            description = description,
            completed = isCompleted,
            createdAt = createdAt,
            userId = userId
        )
    }
