package com.example.textl.DI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.textl.domain.usecase.AuthUseCase
import com.example.textl.domain.usecase.TaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase,
    private val authUseCase: AuthUseCase
) : ViewModel() {
    fun createTask(title: String, description: String?) {
        viewModelScope.launch {
            taskUseCase.createTask(
                tittle = title,
                description = description,
                userId =
            )
        }
    }
}