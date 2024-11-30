package com.example.eventplanner

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BookingActivity : AppCompatActivity() {

    // Declare the views manually
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var btnConfirmBooking: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        // Find the views using findViewById
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)
        btnConfirmBooking = findViewById(R.id.btnConfirmBooking)

        // Get the event name passed from the previous activity
        val eventName = intent.getStringExtra("eventName")
        title = "Booking for $eventName" // Set the title of the activity

        // Confirm booking button click listener
        btnConfirmBooking.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val phone = etPhone.text.toString()

            // Check if all details are filled
            if (name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty()) {
                Toast.makeText(this, "Booking confirmed for $eventName!", Toast.LENGTH_LONG).show()
                finish() // Close the activity after booking confirmation
            } else {
                Toast.makeText(this, "Please fill in all details.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
