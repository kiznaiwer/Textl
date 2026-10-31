package com.example.textl.UI.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.textl.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    private val _events = MutableSharedFlow<String>()
    val events = _events.asSharedFlow()

    fun register(
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {

            if (email.isBlank() || password.isBlank()) {
                _events.emit("Fill all fields")
                return@launch
            }

            val result = authUseCase.register(email, password)

            result
                .onSuccess {
                    onSuccess()
                }
                .onFailure {
                    _events.emit(it.message ?: "Unknown error")
                }
        }
    }
}