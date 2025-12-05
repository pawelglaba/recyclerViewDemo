package com.example.recyclerdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Activity displaying a list of patients using LazyColumn.
 *
 * This activity sets the UI content to the PatientLazyListScreen(),
 * which shows a scrollable list of patients that can be dynamically modified.
 */
class LazyListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setContent launches the Compose UI tree.
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Main screen displaying the patient list
                    PatientLazyListScreen()
                }
            }
        }
    }
}

/**
 * Composable function that builds the UI containing a dynamic list of patients.
 *
 * - Uses remember + mutableStateListOf to keep a reactive list of patients.
 * - LazyColumn efficiently displays items with automatic recycling.
 * - Removing a patient from the list updates the UI automatically.
 */
@Composable
fun PatientLazyListScreen() {

    // remember keeps the list in memory across recompositions
    val patients: SnapshotStateList<Patient> = remember {
        mutableStateListOf(
            Patient("John Doe", 30, R.drawable.patient1),
            Patient("Jane Smith", 25, R.drawable.patient2),
            Patient("Michael Johnson", 40, R.drawable.patient3),
            Patient("Jan Kowalski", 35, R.drawable.patient4),
            Patient("David Lee", 28, R.drawable.patient5),
            Patient("James Brown", 45, R.drawable.patient6)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Lista pacjentów – LazyColumn",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // LazyColumn works like RecyclerView, but automatically handles recycling & state.
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = patients,
                key = { it.name } // unique key improves performance
            ) { patient ->

                // Each item displays a PatientCard with delete button
                PatientCard(
                    patient = patient,
                    onDeleteClick = {
                        patients.remove(patient) // removing triggers recomposition
                    }
                )
            }
        }
    }
}

/**
 * A single patient's UI card.
 *
 * Displays:
 * - Photo
 * - Name
 * - Age
 * - Delete button
 *
 * Pressing the delete button triggers the passed callback.
 */
@Composable
fun PatientCard(
    patient: Patient,
    onDeleteClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Patient image loaded from drawable resources
        Image(
            painter = painterResource(id = patient.imageResId),
            contentDescription = "Patient photo",
            modifier = Modifier
                .size(100.dp)
                .padding(vertical = 8.dp),
            contentScale = ContentScale.Crop // crop image to fill the box
        )

        // Patient name in bold font
        Text(
            text = patient.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        // Simple age label
        Text(
            text = "Age: ${patient.age}",
            fontSize = 16.sp,
            modifier = Modifier
                .padding(start = 8.dp, bottom = 8.dp)
        )

        // Button calling the delete callback
        Button(
            onClick = onDeleteClick,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text("Delete")
        }
    }
}
