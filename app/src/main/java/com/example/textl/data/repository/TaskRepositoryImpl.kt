package com.example.textl.data.repository

import com.example.textl.data.dto.TaskDto
import com.example.textl.data.mapper.toDomain
import com.example.textl.domain.model.Task
import com.example.textl.domain.repository.TaskRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): TaskRepository {

    private val tasksCollection = firestore.collection("tasks")
    override fun observeTasks(userId: String): Flow<List<Task>> = callbackFlow{
        val listener = tasksCollection
            .whereEqualTo("userId", userId)
            .addSnapshotListener { snapshot, error ->
                if (error != null){
                    close(error)
                    return@addSnapshotListener
                }

                val tasks = snapshot?.documents
                    ?.mapNotNull { doc ->
                        doc.toObject(TaskDto::class.java)?.copy(id = doc.id)
                    }
                    ?.map { it.toDomain() }
                    ?: emptyList()

                trySend(tasks)
            }

        awaitClose {
            listener.remove()
        }
    }

    override suspend fun createTask(
        title: String,
        description: String?,
        userId: String
    ): Result<Unit> {
        return try{

            val task = TaskDto(
                title = title,
                description = description,
                isCompleted = false,
                createdAt = System.currentTimeMillis(),
                userId = userId
            )

            tasksCollection.add(task).await()

            Result.success(Unit)
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun deleteTask(taskId: String): Result<Unit> {
        return try {
            tasksCollection
                .document(taskId)
                .delete()
                .await()

            Result.success(Unit)
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun updateTaskCompletion(
        taskId: String,
        isCompleted: Boolean
    ): Result<Unit> {
        return try{
            tasksCollection
                .document(taskId)
                .update("isCompleted", isCompleted)
                .await()

            Result.success(Unit)
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}