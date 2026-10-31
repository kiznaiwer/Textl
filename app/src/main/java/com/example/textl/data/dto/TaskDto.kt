package com.example.textl.data.dto

data class TaskDto (
    val title: String = "",
    val description: String? = null,
    val completed: Boolean = false,
    val createdAt: Long = 0L,
    val userId: String = ""
)