package com.example.recyclerdemo

/**
 * Data class representing a single patient.
 *
 * This model is used both by RecyclerView (PatientAdapter)
 * and Jetpack Compose (LazyColumn) to display patient information.
 *
 * @property name The full name of the patient.
 * @property age The patient's age in years.
 * @property imageResId Resource ID of the patient's photo stored in /res/drawable.
 *
 * The class is a Kotlin data class, which automatically provides:
 *  - equals() / hashCode()
 *  - toString()
 *  - componentN() functions
 *  - copy() function
 *
 * Data classes are ideal for UI lists because they are lightweight
 * and represent simple immutable models.
 */
data class Patient(
    val name: String,
    val age: Int,
    val imageResId: Int
)
