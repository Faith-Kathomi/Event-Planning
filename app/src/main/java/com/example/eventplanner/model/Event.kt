package com.example.eventplanner.models

data class Event(
    val id: String = "",
    val title: String,
    val description: String,
    val date: String,
    val location: String,
    val createdBy: String, // User ID or other unique identifier
    val imageUrl: String
)
