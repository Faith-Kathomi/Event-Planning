package com.example.eventplanner

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.eventplanner.models.Event
import com.example.eventplanner.EventAdapter
import com.example.eventplanner.MainActivity
import com.example.eventplanner.R

class ListingsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnBackToGallery: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listings)

        // Access views using findViewById
        recyclerView = findViewById(R.id.recyclerView)
        btnBackToGallery = findViewById(R.id.btnBackToGallery)

        // Sample data for events with image URLs
        val events = listOf(
            Event(
                title = "Bustani Gardens",
                description = "A beautiful garden for events.",
                date = "2024-06-15",
                location = "Nairobi, Kenya",
                createdBy = "user123", // You can replace this with actual user data if available
                imageUrl = "https://www.example.com/images/camping.jpg"
            ),
            Event(
                title = "Serene Gardens",
                description = "A serene location for a peaceful event.",
                date = "2024-07-20",
                location = "Mombasa, Kenya",
                createdBy = "user124",
                imageUrl = "https://www.example.com/images/nature.jpg"
            ),
            Event(
                title = "Naishola Gardens",
                description = "Perfect for weddings and large gatherings.",
                date = "2024-08-10",
                location = "Nakuru, Kenya",
                createdBy = "user125",
                imageUrl = "https://www.example.com/images/birthday.jpg"
            ),
            Event(
                title = "Orchid Gardens",
                description = "Known for its vast orchid collection.",
                date = "2024-09-01",
                location = "Kisumu, Kenya",
                createdBy = "user126",
                imageUrl = "https://www.example.com/images/bench.jpg"
            )
        )

        // Set up RecyclerView
        val adapter = EventAdapter(events, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Back to Gallery Button
        btnBackToGallery.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
