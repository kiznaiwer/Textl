package com.example.textl.UI.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.textl.UI.theme.ThemeViewModel
import com.example.textl.UI.auth.LoginScreen
import com.example.textl.UI.auth.RegisterScreen
import com.example.textl.UI.task.TaskListScreen

@Composable
fun AppNavGraph(themeViewModel: ThemeViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN){
            LoginScreen(navController)
        }
        composable(Routes.REGISTER){
            RegisterScreen(navController)
        }
        composable(Routes.TASKS){
            TaskListScreen(themeViewModel)
        }
    }
}