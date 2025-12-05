package com.example.recyclerdemo

import PatientAdapter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Main activity of the application.
 *
 * Displays a list of patients using RecyclerView.
 * RecyclerView requires:
 *  - a LayoutManager (how items should be arranged)
 *  - an Adapter (how items should be displayed)
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content view to the layout file that contains RecyclerView.
        // Without this, RecyclerView won't appear on screen.
        setContentView(R.layout.activity_main)

        // Create a list of patients that will be displayed in RecyclerView.
        // This list is passed to the adapter and defines the UI content.
        val patients = mutableListOf(
            Patient("John Doe", 30, R.drawable.patient1),
            Patient("Jane Smith", 25, R.drawable.patient2),
            Patient("Michael Johnson", 40, R.drawable.patient3),
            Patient("Jan Kowalski", 35, R.drawable.patient4),
            Patient("David Lee", 28, R.drawable.patient5),
            Patient("James Brown", 45, R.drawable.patient6)
        )

        // Find RecyclerView defined in activity_main.xml by its ID.
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Create an instance of the adapter.
        // The adapter knows how to convert each Patient object into a list item view.
        val adapter = PatientAdapter(patients)

        // Assign the adapter to RecyclerView.
        recyclerView.adapter = adapter

        // Set the LayoutManager responsible for arranging items vertically (one under another).
        // LinearLayoutManager is the simplest type of layout manager (like a standard list).
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
