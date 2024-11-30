package com.example.eventplanner.ui.screens

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eventplanner.components.InputField
import com.example.eventplanner.data.FirebaseService
import com.example.eventplanner.models.Event
import com.example.eventplanner.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun CreateEventScreen(navController: NavController) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    // Get the current signed-in user
    val currentUser = FirebaseAuth.getInstance().currentUser

    // If user is not signed in, navigate to the login screen
    if (currentUser == null) {
        navController.navigate(Screen.Login.route)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Create Event",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        InputField(
            label = "Event Title",
            value = title,
            onValueChange = { title = it }
        )
        Spacer(modifier = Modifier.height(8.dp))
        InputField(
            label = "Event Description",
            value = description,
            onValueChange = { description = it }
        )
        Spacer(modifier = Modifier.height(8.dp))
        InputField(
            label = "Event Date",
            value = date,
            onValueChange = { date = it }
        )
        Spacer(modifier = Modifier.height(8.dp))
        InputField(
            label = "Event Location",
            value = location,
            onValueChange = { location = it }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Check if the user is signed in and use their UID
                if (currentUser != null) {
                    val event = Event(
                        title = title,
                        description = description,
                        date = date,
                        location = location,
                        createdBy = currentUser.uid, // Use the signed-in user's UID
                        imageUrl = "" // Default empty imageUrl or provide a placeholder URL
                    )

                    coroutineScope.launch {
                        // Save to Firestore
                        FirebaseService.createEventInFirestore(event) { firestoreSuccess ->
                            if (firestoreSuccess) {
                                println("Event saved to Firestore.")
                            } else {
                                println("Failed to save event to Firestore.")
                            }
                        }

                        // Save to Realtime Database
                        FirebaseService.createEventInRealtimeDatabase(event) { realtimeSuccess ->
                            if (realtimeSuccess) {
                                println("Event saved to Realtime Database.")
                                // Navigate to the event list screen after saving
                                navController.navigate(Screen.EventList.route)
                            } else {
                                println("Failed to save event to Realtime Database.")
                            }
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create Event")
        }
    }
}
