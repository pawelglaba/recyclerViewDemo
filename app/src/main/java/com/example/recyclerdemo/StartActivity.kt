// StartActivity.kt
package com.example.recyclerdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

/**
 * Entry activity of the application.
 *
 * This screen displays two buttons that allow the user to choose
 * between two different list implementations:
 *
 *  - RecyclerViewActivity (classic View system)
 *  - LazyListActivity (Jetpack Compose version)
 *
 * The purpose of this activity is to demonstrate the difference
 * between RecyclerView and LazyColumn in a simple, user-friendly way.
 */
class StartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Loads the XML layout containing two navigation buttons.
        setContentView(R.layout.activity_start)

        // Retrieve references to UI buttons from the layout.
        val recyclerButton = findViewById<Button>(R.id.recyclerButton)
        val lazyListButton = findViewById<Button>(R.id.lazyListButton)

        // Navigates to the RecyclerView demo activity when clicked.
        recyclerButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        // Navigates to the Jetpack Compose LazyColumn demo activity.
        lazyListButton.setOnClickListener {
            startActivity(Intent(this, LazyListActivity::class.java))
        }
    }
}
