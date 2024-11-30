package com.example.eventplanner.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ChecklistScreen(navController: NavController) {
    val tasks = listOf("Create e-invite", "Invite Guests", "Order Food", "Hire a Decorator")
    val completedTasks = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Checklist",
            style = MaterialTheme.typography.headlineMedium // Updated to Material 3 style
        )
        Spacer(modifier = Modifier.height(8.dp))
        tasks.forEach { task ->
            Row(modifier = Modifier.padding(8.dp)) {
                Checkbox(
                    checked = completedTasks.contains(task),
                    onCheckedChange = {
                        if (it) completedTasks.add(task) else completedTasks.remove(task)
                    }
                )
                Text(task, modifier = Modifier.padding(start = 8.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("home") }) {
            Text("Back to Home")
        }
    }
}
