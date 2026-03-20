package com.example.textl.data.dto

data class TaskDto (
    val id: String = "",
    val title: String = "",
    val description: String? = null,
    val isCompleted: Boolean = false,
    val createdAt: Long = 0L,
    val userId: String = ""
)