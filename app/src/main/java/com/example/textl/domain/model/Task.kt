package com.example.textl.domain.model
import java.time.Instant

data class Task(
    val id: String,
    val title: String,
    val description: String?,
    val isCompleted: Boolean,
    val createdAt: Long,
    val userId: String
)
