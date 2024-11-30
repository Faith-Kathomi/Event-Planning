package com.example.eventflow

import android.content.Intent
import android.net.Uri // Import this
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContactInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_info)

        val contactButton = findViewById<Button>(R.id.contactVendorButton)
        contactButton.setOnClickListener {
            val phoneNumber = "+254722724684" // Use the actual vendor phone number
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            startActivity(intent)
            }
        val billingButton = findViewById<Button>(R.id.goToBillingButton)
        billingButton.setOnClickListener {
            val intent = Intent(this, BillingActivity::class.java)
            startActivity(intent)
        }
        // Set up the Location Link button
        val locationLink = findViewById<TextView>(R.id.location_link)
        locationLink.setOnClickListener {
            val locationUrl = "https://g.co/kgs/2Bzt8bF"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))
            startActivity(intent)
        }
        val goToNaisholaGardenButton: Button = findViewById(R.id.goToNaisholaGardenButton)
        goToNaisholaGardenButton.setOnClickListener {
            val intent = Intent(this, NaisholaGardenActivity::class.java)
            startActivity(intent)
        }

    }
}
