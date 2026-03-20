package com.example.textl.domain.repository
import com.example.textl.domain.model.User

interface AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): Result<Unit>

    suspend fun register(
        email: String,
        password: String
    ): Result<Unit>

    fun getCurrentUser(): User?

    suspend fun logout()
}
