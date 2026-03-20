package com.example.textl.UI.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.textl.domain.usecase.AuthUseCase
import com.example.textl.domain.usecase.TaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase,
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private val userId = authUseCase.getCurrentUser()?.id ?: ""

    val tasks = taskUseCase
        .observeTasks(userId)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
}