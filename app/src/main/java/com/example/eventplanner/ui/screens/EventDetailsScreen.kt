package com.example.eventplanner.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun EventDetailsScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Event Details",
            style = MaterialTheme.typography.headlineMedium // Updated for Material 3
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Name: Sara’s Birthday Bash", style = MaterialTheme.typography.bodyLarge)
        Text("Date: 30-10-2021", style = MaterialTheme.typography.bodyLarge)
        Text("Time: 08:00 AM", style = MaterialTheme.typography.bodyLarge)
        Text("Budget: ₹ 10,000", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("checklist") }) {
            Text("View Checklist")
        }
    }
}
