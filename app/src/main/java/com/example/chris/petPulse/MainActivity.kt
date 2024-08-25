package com.example.chris.petPulse

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.petPulse.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    // Replace mutableStateOf with regular var variables
    private var hunger = 50
    private var happiness = 50

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Check and request notification permission only if on Android 13 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 0)
            }
        }

        // Find views by their IDs
        val petImageView: ImageView = findViewById(R.id.petImage)
        val hungerStatusTextView: TextView = findViewById(R.id.hungerStatus)
        val happinessStatusTextView: TextView = findViewById(R.id.happinessStatus)
        val feedButton: Button = findViewById(R.id.feedButton)
        val playButton: Button = findViewById(R.id.playButton)

        // Update UI every minute
        lifecycleScope.launch {
            while (true) {
                delay(60000) // Every minute
                hunger = (hunger + 5).coerceAtMost(100)
                happiness = (happiness - 5).coerceAtLeast(0)
                updateUI(hungerStatusTextView, happinessStatusTextView)
            }
        }

        // Set button click listeners
        feedButton.setOnClickListener {
            hunger = (hunger - 10).coerceAtLeast(0)
            happiness = (happiness + 5).coerceAtMost(100)
            updateUI(hungerStatusTextView, happinessStatusTextView)
        }

        playButton.setOnClickListener {
            happiness = (happiness + 10).coerceAtMost(100)
            updateUI(happinessStatusTextView)
        }
    }

    // Function to update the UI with the current hunger and happiness levels
    private fun updateUI(hungerStatusTextView: TextView, happinessStatusTextView: TextView) {
        hungerStatusTextView.text = "Hunger: $hunger"
        happinessStatusTextView.text = "Happiness: $happiness"
    }

    private fun updateUI(happinessStatusTextView: TextView) {
        happinessStatusTextView.text = "Happiness: $happiness"
    }
}
