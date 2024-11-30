package com.example.eventplanner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventplanner.data.FirebaseService
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    // Register with email and password
    fun registerWithEmail(
        email: String,
        password: String,
        onComplete: (Boolean, String?) -> Unit // Adjusted the callback type
    ) {
        viewModelScope.launch {
            FirebaseService.registerWithEmail(email, password, onComplete)
        }
    }

    // Sign in with Google using idToken
    fun signInWithGoogle(idToken: String, onComplete: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            try {
                val credential: AuthCredential = GoogleAuthProvider.getCredential(idToken, null)
                FirebaseService.signInWithGoogle(credential) { success ->
                    onComplete(success, if (!success) "Google Sign-In failed" else null)
                }
            } catch (e: Exception) {
                onComplete(false, e.message)
                println("Google Sign-In Error: ${e.message}")
            }
        }
    }
}
