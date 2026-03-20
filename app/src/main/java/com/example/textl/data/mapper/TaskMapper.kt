package com.example.textl.data.mapper

import com.example.textl.data.dto.TaskDto
import com.example.textl.domain.model.Task


    fun TaskDto.toDomain(): Task {
        return Task(
            id = id,
            title = title,
            description = description,
            isCompleted = isCompleted,
            createdAt = createdAt,
            userId = userId
        )
    }

    fun Task.toDto(): TaskDto {
        return TaskDto(
            id = id,
            title = title,
            description = description,
            isCompleted = isCompleted,
            createdAt = createdAt,
            userId = userId
        )
    }
