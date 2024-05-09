package com.example.recyclerdemo

/**
 * Klasa reprezentująca pacjenta.
 *
 * @property name Imię pacjenta.
 * @property age Wiek pacjenta.
 * @property imageResId Identyfikator zasobu obrazu pacjenta.
 */
data class Patient(val name: String, val age: Int, val imageResId: Int)
