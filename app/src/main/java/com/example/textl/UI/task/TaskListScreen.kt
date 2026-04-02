package com.example.textl.UI.task

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.textl.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.textl.UI.ThemeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    themeViewModel: ThemeViewModel,
    viewModel: TaskViewModel = hiltViewModel()
) {
    val tasks by viewModel.tasks.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.task)) },
                actions = {
                    IconButton(
                        onClick = { themeViewModel.toggleTheme() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.DarkMode,
                            contentDescription = "Toggle theme"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true }
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { padding ->

        LazyColumn(modifier = Modifier.padding(padding)) {
            items(tasks) { task ->
                TaskItem(
                    task = task,
                    onCheckedChange = {
                        viewModel.updateTask(task.id, it)
                    },
                    onDeleteClick = {
                        viewModel.deleteTask(task.id)
                    }
                )
            }
        }

        if (showDialog) {
            CreateTaskDialog(
                onDismiss = { showDialog = false },
                onCreate = { title, desc ->
                    viewModel.createTask(title, desc)
                    showDialog = false
                }
            )
        }
    }
}