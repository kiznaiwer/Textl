package com.example.textl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.textl.UI.theme.TextlTheme
import com.example.textl.UI.theme.ThemeViewModel
import com.example.textl.UI.navigation.AppNavGraph
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val themeViewModel: ThemeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDark by themeViewModel.isDark.collectAsState()

            TextlTheme(darkTheme = isDark) {
                AppNavGraph(themeViewModel)
            }
        }
    }
}
