package com.example.textl.domain.usecase

import com.example.textl.domain.repository.TaskRepository

class TaskUseCase(
    private val repository: TaskRepository
) {

    fun observeTasks(userId: String) = repository.observeTasks(userId)

    suspend fun createTask(
        title: String,
        description: String?,
        userId: String
    ): Result<Unit> {
        return repository.createTask(title, description, userId)
    }

    suspend fun deleteTask(
        taskId: String
    ): Result<Unit> {
        return repository.deleteTask(taskId)
    }

    suspend fun updateTaskCompletion(
        taskId: String,
        isCompleted: Boolean
    ): Result<Unit> {
        return repository.updateTaskCompletion(taskId, isCompleted)
    }
}