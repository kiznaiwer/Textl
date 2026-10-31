package com.example.textl.UI.theme

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ThemeViewModel : ViewModel() {
    private val _isDark = MutableStateFlow(false)
    val isDark = _isDark.asStateFlow()

    fun toggleTheme() {
        _isDark.value = !_isDark.value
    }
}