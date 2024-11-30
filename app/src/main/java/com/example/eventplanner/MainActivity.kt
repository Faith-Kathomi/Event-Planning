package com.example.eventplanner

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.eventplanner.ui.theme.EventPlannerTheme
import com.example.eventplanner.ListingsActivity
import com.example.eventplanner.navigation.AppNavigation
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class MainActivity : ComponentActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: com.google.android.gms.auth.api.signin.GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()

        // Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))  // Replace with your Firebase Web Client ID
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Register ActivityResultLauncher for Google Sign-In
        val signInLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                val data = result.data
                try {
                    val account = GoogleSignIn.getSignedInAccountFromIntent(data)
                        .getResult(ApiException::class.java)
                    if (account != null) {
                        val credential: AuthCredential =
                            GoogleAuthProvider.getCredential(account.idToken, null)
                        firebaseAuth.signInWithCredential(credential)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(
                                        this,
                                        "Google Sign-In Successful!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    // Navigate to ListingsActivity after successful login
                                    val intent = Intent(this, ListingsActivity::class.java)
                                    startActivity(intent)
                                    finish() // Finish MainActivity to prevent user from going back
                                } else {
                                    Toast.makeText(
                                        this,
                                        "Firebase Authentication Failed",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    }
                } catch (e: ApiException) {
                    Toast.makeText(this, "Google Sign-In Failed: ${e.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        val onGoogleSignIn: () -> Unit = {
            googleSignInClient.signOut().addOnCompleteListener {
                val signInIntent = googleSignInClient.signInIntent
                signInLauncher.launch(signInIntent)
            }
        }

        // Set the content view with Compose navigation
        setContent {
            EventPlannerTheme {
                val navController = rememberNavController()

                // Directly navigate after Google Sign-In
                AppNavigation(
                    navController = navController,
                    onGoogleSignIn = onGoogleSignIn // Pass onGoogleSignIn function to AppNavigation
                )
            }
        }
    }
}
