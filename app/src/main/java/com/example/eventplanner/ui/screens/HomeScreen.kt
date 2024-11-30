package com.example.eventplanner.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@Composable
fun HomeScreen(navController: NavController) {
    // Column to hold the content
    Column(modifier = Modifier.fillMaxSize()) {
        // Row to hold the Create Event button at the top-right corner
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Padding to position the button properly
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = { navController.navigate("create_event") }) {
                Text("Create Event")
            }
        }

        // Title Text
        Text(
            text = "Welcome to EventFlow",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            style = androidx.compose.material3.MaterialTheme.typography.headlineLarge
        )

        // Display past event images from the internet
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Event 1: Graduation (Use URL for the image)
            Image(
                painter = rememberImagePainter("https://www.example.com/graduation.jpg"), // Replace with a valid image URL
                contentDescription = "Graduation Event",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )

            // Event 2: Birthday (Use URL for the image)
            Image(
                painter = rememberImagePainter("https://images.app.goo.gl/rE4mGysLrp9QjJB78"), // Replace with a valid image URL
                contentDescription = "Birthday Event",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )

            // Event 3: Wedding (Use URL for the image)
            Image(
                painter = rememberImagePainter("https://images.app.goo.gl/HxrXjyVX2vhZnrAQA"), // Replace with a valid image URL
                contentDescription = "Wedding Event",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}
