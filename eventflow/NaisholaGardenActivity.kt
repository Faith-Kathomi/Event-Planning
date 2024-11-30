package com.example.eventflow

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class NaisholaGardenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_naishola_garden)

        val contactButton = findViewById<Button>(R.id.contactVendorButton)
        contactButton.setOnClickListener {
            val phoneNumber = "+254795656433" // Use the actual vendor phone number
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

        // Visit Website Button
        val visitWebsiteButton: Button = findViewById(R.id.visitWebsiteButton)
        visitWebsiteButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://naishola.com/"))
            startActivity(browserIntent)
        }

        // View Location Button
        val viewLocationButton: Button = findViewById(R.id.viewLocationButton)
        viewLocationButton.setOnClickListener {
            val mapIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("geo:-1.2718584,36.8172448?q=-1.2718584,36.8172448(Naishola+Garden)")
            )
            startActivity(mapIntent)
        }
    }
}
