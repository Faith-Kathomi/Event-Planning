// repository/EventRepository.kt
import com.example.eventplanner.models.Event  // Corrected the import path to models
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class EventRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val eventsCollection = firestore.collection("events")

    // Fetch the list of events
    suspend fun getEvents(): List<Event> {
        return try {
            eventsCollection.get().await().toObjects(Event::class.java)
        } catch (e: Exception) {
            emptyList()  // Return an empty list in case of an error
        }
    }

    // Add a new event to the Firestore
    suspend fun addEvent(event: Event) {
        eventsCollection.add(event).await()
    }
}
