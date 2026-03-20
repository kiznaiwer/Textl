package com.example.textl.UI.auth

import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.textl.UI.navigation.Routes

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it},
            label = { Text("Email")}
        )

        Spacer(Modifier.height(8.dp))

        TextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            label = { Text("Password")}
        )

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.login(
                    onSuccess = {
                        navController.navigate(Routes.TASKS){
                            popUpTo(Routes.LOGIN) { inclusive = true}
                        }
                    },
                    onError = {}
                )
            }
        ) {
            Text("Login")
        }

        Spacer(Modifier.height(8.dp))

        TextButton(
            onClick = {
                navController.navigate(Routes.REGISTER)
            }
        ) { Text("Register")}
    }
}