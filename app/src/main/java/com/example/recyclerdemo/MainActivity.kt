package com.example.recyclerdemo

import PatientAdapter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Główna aktywność aplikacji, która wyświetla listę pacjentów za pomocą RecyclerView.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Lista pacjentów do wyświetlenia w RecyclerView
        val patients = mutableListOf(
            Patient("John Doe", 30, R.drawable.patient1),
            Patient("Jane Smith", 25, R.drawable.patient2),
            Patient("Michael Johnson", 40, R.drawable.patient3),
            Patient("Jan Kowalski", 35, R.drawable.patient4),
            Patient("David Lee", 28, R.drawable.patient5),
            Patient("James Brown", 45, R.drawable.patient6)
        )

        // Inicjalizacja RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Ustawienie adaptera dla RecyclerView, który będzie obsługiwał listę pacjentów
        val adapter = PatientAdapter(patients)
        recyclerView.adapter = adapter

        // Ustawienie menedżera układu dla RecyclerView, w tym przypadku używamy LinearLayoutManager
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
