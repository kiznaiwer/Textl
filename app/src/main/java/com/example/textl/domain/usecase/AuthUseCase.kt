package com.example.textl.domain.usecase

import com.example.textl.domain.model.User
import com.example.textl.domain.repository.AuthRepository

class AuthUseCase(
    private val repository: AuthRepository
) {

    suspend fun login(
        email: String,
        password: String
    ): Result<Unit>{
        return repository.login(email, password)
    }

    suspend fun register(
        email: String,
        password: String
    ) : Result<Unit>{
        return repository.register(email, password)
    }

    fun getCurrentUser(): User? {
        return repository.getCurrentUser()
    }

    suspend fun logout(){
        repository.logout()
    }
}