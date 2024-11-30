package com.example.eventplanner.ui.screens

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eventplanner.components.InputField
import com.example.eventplanner.data.FirebaseService
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val signInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            val account = task?.result
            if (account != null) {
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                FirebaseService.signInWithGoogle(credential) { success ->
                    if (success) {
                        navController.navigate("home")
                    } else {
                        errorMessage = "Google Sign-In failed"
                    }
                }
            }
        } else {
            errorMessage = "Google Sign-In canceled"
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
            text = "Register",
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

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                if (email.isBlank() || password.isBlank()) {
                    errorMessage = "Email and password cannot be blank"
                    return@Button
                }

                coroutineScope.launch {
                    FirebaseService.registerWithEmail(email, password) { success, error ->
                        if (success) {
                            navController.navigate("home")
                        } else {
                            errorMessage = error ?: "Registration failed"
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register with Email")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val googleSignInClient = FirebaseService.getGoogleSignInClient(context)
                val signInIntent = googleSignInClient.signInIntent
                signInLauncher.launch(signInIntent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign in with Google")
        }
    }
}
