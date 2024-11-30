package com.example.eventplanner.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.eventplanner.components.InputField
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navController: NavController, onGoogleSignIn: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    // Function to handle email/password login
    fun loginWithEmail() {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Navigate to home screen after successful login
                        navController.navigate("home")
                    } else {
                        errorMessage = "Login failed: ${task.exception?.message}"
                    }
                }
        } else {
            errorMessage = "Please enter both email and password"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        InputField(
            label = "Email",
            value = email,
            onValueChange = { email = it }
        )
        Spacer(modifier = Modifier.height(8.dp))
        InputField(
            label = "Password",
            value = password,
            onValueChange = { password = it },
            isPassword = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Show error message if any
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = { loginWithEmail() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                onGoogleSignIn()  // Trigger Google Sign-In process
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign in with Google")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Don't have an account? Register",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable {
                    navController.navigate("registration")
                }
        )
    }
}
