package com.example.eventflow

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BillingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_billing)

        // Set up the Pay Now button (Card Payment)
        val payNowButton: Button = findViewById(R.id.pay_now_button)
        payNowButton.setOnClickListener {
            Toast.makeText(this, "Card Payment Submitted!", Toast.LENGTH_SHORT).show()
        }

        // Set up the Pay Now button (M-Pesa Payment)
        val mpesaPaymentButton: Button = findViewById(R.id.mpesa_payment_button)
        mpesaPaymentButton.setOnClickListener {
            // Simulating an M-Pesa payment (Use actual integration in production)
            val mpesaUSSDCode = "*234#"
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$mpesaUSSDCode"))
            startActivity(intent)
        }

    }
}
