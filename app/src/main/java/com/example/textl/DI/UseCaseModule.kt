package com.example.textl.DI

import com.example.textl.domain.repository.AuthRepository
import com.example.textl.domain.repository.TaskRepository
import com.example.textl.domain.usecase.AuthUseCase
import com.example.textl.domain.usecase.TaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule{

    @Provides
    @Singleton
    fun provideAuthUseCase(
        repository: AuthRepository
    ): AuthUseCase {
        return AuthUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideTaskUseCase(
        repository: TaskRepository
    ): TaskUseCase {
        return TaskUseCase(repository)
    }
}