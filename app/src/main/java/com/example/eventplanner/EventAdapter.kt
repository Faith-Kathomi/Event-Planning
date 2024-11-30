package com.example.eventplanner

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eventplanner.models.Event
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.google.android.material.imageview.ShapeableImageView
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

class EventAdapter(
    private val events: List<Event>,
    private val context: Context
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgEvent: ShapeableImageView = itemView.findViewById(R.id.imgEvent)
        private val txtEventName: MaterialTextView = itemView.findViewById(R.id.txtEventName)
        private val btnBookNow: MaterialButton = itemView.findViewById(R.id.btnBookNow)

        fun bind(event: Event) {
            // Use Coil to load the image from the URL
            val imageUrl = event.imageUrl


            // Set the event title to the TextView
            txtEventName.text = event.title

            // Handle Book Now button click
            btnBookNow.setOnClickListener {
                val intent = Intent(context, BookingActivity::class.java)
                intent.putExtra("eventName", event.title)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int = events.size
}
