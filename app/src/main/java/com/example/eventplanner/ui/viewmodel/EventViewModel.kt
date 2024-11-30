package com.example.eventplanner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventplanner.data.FirebaseService
import com.example.eventplanner.models.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EventViewModel : ViewModel() {
    private val _events = MutableStateFlow<List<Event>>(emptyList())  // List of Event objects
    val events: StateFlow<List<Event>> = _events

    // Fetch events from Firebase
    fun fetchEvents() {
        viewModelScope.launch {
            val eventsList = FirebaseService.getEvents()  // Fetch events
            _events.value = eventsList  // Update the state with the fetched events
        }
    }
}
