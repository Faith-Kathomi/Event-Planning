package com.example.eventplanner.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventplanner.ui.screens.*

// AppNavigation Composable
@Composable
fun AppNavigation(navController: NavHostController, onGoogleSignIn: () -> Unit) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        // Login screen
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController, onGoogleSignIn = {
                onGoogleSignIn() // Trigger Google Sign-In
                // After successful Google Sign-In, navigate to the home screen
                navController.navigate(Screen.Home.route) {
                    // Clear the back stack so the user can't navigate back to the login screen
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            })
        }

        // Registration screen
        composable(route = Screen.Registration.route) {
            RegistrationScreen(navController = navController)
        }

        // Event list screen
        composable(route = Screen.EventList.route) {
            EventDetailsScreen(navController = navController)
        }

        // Create event screen (protected, user must be authenticated)
        composable(route = Screen.CreateEvent.route) {
            CreateEventScreen(navController = navController)
        }

        // Home screen (protected, user must be authenticated to view)
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
    }
}
