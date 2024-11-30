package com.example.eventplanner.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Registration : Screen("registration") // Add this line
    object EventList : Screen("event_list")
    object CreateEvent : Screen("create_event")
    object Home : Screen("home")
}
