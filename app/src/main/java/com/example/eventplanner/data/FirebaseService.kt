package com.example.eventplanner.data

import android.content.Context
import com.example.eventplanner.R
import com.example.eventplanner.models.Event
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

object FirebaseService {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    private val realtimeDb = FirebaseDatabase.getInstance().reference

    // Function to register user with email and password
    suspend fun registerWithEmail(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        try {
            val result: AuthResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            onResult(result.user != null, null)
        } catch (e: Exception) {
            println("Error during registration: ${e.message}")
            onResult(false, e.message)
        }
    }

    // Function to sign in user with Google credentials
    fun signInWithGoogle(credential: AuthCredential, onResult: (Boolean) -> Unit) {
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                onResult(task.isSuccessful) // This will call the result callback with success status
            }
    }

    // Function to get GoogleSignInClient instance
    fun getGoogleSignInClient(context: Context): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(context, gso)
    }

    // Function to create an event in Firestore
    suspend fun createEventInFirestore(event: Event, onResult: (Boolean) -> Unit) {
        try {
            val eventRef = firestore.collection("events").document()
            eventRef.set(event).await()
            onResult(true)
        } catch (e: Exception) {
            onResult(false)
            e.printStackTrace()
        }
    }

    // Function to create an event in Realtime Database
    suspend fun createEventInRealtimeDatabase(event: Event, onResult: (Boolean) -> Unit) {
        try {
            val eventRef = realtimeDb.child("events").push()
            eventRef.setValue(event).await() // Push event to Realtime Database
            onResult(true)
        } catch (e: Exception) {
            onResult(false)
            e.printStackTrace()
        }
    }

    // Function to fetch events from Firestore
    suspend fun getEvents(): List<Event> {
        return try {
            // Query Firestore to get all events
            val snapshot = firestore.collection("events").get().await()
            // Convert Firestore documents to Event objects
            snapshot.documents.map { doc ->
                Event(
                    title = doc.getString("title") ?: "",
                    description = doc.getString("description") ?: "",
                    date = doc.getString("date") ?: "",
                    location = doc.getString("location") ?: "",
                    createdBy = doc.getString("createdBy") ?: "",
                    imageUrl = doc.getString("imageUrl") ?: "" // Ensure imageUrl is included
                )
            }
        } catch (e: Exception) {
            println("Error fetching events: ${e.message}")
            emptyList() // Return an empty list if there's an error
        }
    }
}
