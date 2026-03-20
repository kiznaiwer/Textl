package com.example.textl.domain.repository
import com.example.textl.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun observeTasks(
        userId: String
    ): Flow<List<Task>>

    suspend fun createTask(
        title: String,
        description: String?,
        userId: String
    ): Result<Unit>

    suspend fun deleteTask(
        taskId: String
    ): Result<Unit>

    suspend fun updateTaskCompletion(
        taskId: String,
        isCompleted: Boolean
    ): Result<Unit>
}
