import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerdemo.Patient
import com.example.recyclerdemo.R

/**
 * Adapter responsible for displaying a list of patients in a RecyclerView.
 *
 * RecyclerView works by:
 *  - creating only a limited number of item views (ViewHolders),
 *  - recycling them when they scroll off-screen,
 *  - and binding new data to reused views.
 *
 * The adapter provides the logic for:
 *  - creating new ViewHolder objects,
 *  - binding patient data to each ViewHolder,
 *  - reporting how many items exist.
 *
 * @property patients Mutable list of Patient objects displayed in RecyclerView.
 */
class PatientAdapter(
    private val patients: MutableList<Patient>
) : RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    /**
     * ViewHolder stores references to the UI elements inside a single item view.
     *
     * Thanks to ViewHolder:
     *  - we avoid calling findViewById() repeatedly,
     *  - performance is improved (especially for long lists).
     *
     * @param itemView The inflated XML layout representing one list item.
     */
    class PatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val ageTextView: TextView = itemView.findViewById(R.id.ageTextView)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }

    /**
     * Called when RecyclerView needs a new ViewHolder.
     *
     * This method:
     *  - inflates the XML layout (patient_item.xml),
     *  - creates a new ViewHolder containing the inflated view.
     *
     * @param parent The parent ViewGroup into which the new view will be added.
     * @param viewType View type (used if the list contains different item types).
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        // Inflate the layout for a single item row
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.patient_item, parent, false)
        return PatientViewHolder(itemView)
    }

    /**
     * Binds data to the given ViewHolder for the item at the specified position.
     *
     * When an item scrolls on screen or needs updating:
     *  - this method is called,
     *  - the appropriate patient data is assigned to UI elements in the ViewHolder.
     *
     * @param holder The ViewHolder containing the item views.
     * @param position Index of the item in the dataset.
     */
    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val currentPatient = patients[position]

        // Populate UI with patient data
        holder.nameTextView.text = currentPatient.name
        holder.ageTextView.text = currentPatient.age.toString()
        holder.imageView.setImageResource(currentPatient.imageResId)

        // Handle "Delete" button press
        holder.deleteButton.setOnClickListener {
            removePatient(currentPatient)
        }
    }

    /**
     * Returns the total number of items in the list.
     *
     * RecyclerView uses this to determine how many items it should draw.
     */
    override fun getItemCount(): Int = patients.size

    /**
     * Removes a patient from the list and notifies RecyclerView about the change.
     *
     * notifyItemRemoved(position) ensures:
     *  - animations are applied,
     *  - list updates efficiently,
     *  - only the removed item is re-drawn (not the whole list).
     *
     * @param patient The patient to remove.
     */
    private fun removePatient(patient: Patient) {
        val position = patients.indexOf(patient)
        if (position != -1) {
            patients.removeAt(position)
            notifyItemRemoved(position)

            if (patients.isEmpty()) {
                println("Pusta lista") // For debugging purposes
            }
        }
    }
}
