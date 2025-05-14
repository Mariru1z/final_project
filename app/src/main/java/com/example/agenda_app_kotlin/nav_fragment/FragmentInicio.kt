package com.example.agenda_app_kotlin.nav_fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.agenda_app_kotlin.R
import com.example.agenda_app_kotlin.model.Tarea
import com.example.agenda_app_kotlin.Bottom_Nav_Fragment.FragmentTareas
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*


class FragmentInicio : Fragment() {

    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_inicio, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firestore = FirebaseFirestore.getInstance()

        val fab: FloatingActionButton = view.findViewById(R.id.addFab)
        fab.setOnClickListener {
            mostrarDialogoCrearTarea()
        }

        val bottomNav: BottomNavigationView = view.findViewById(R.id.bottonNavigation)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_tareas -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.bottomFragment, FragmentTareas())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }

    private fun mostrarDialogoCrearTarea() {
        val layout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 40, 50, 10)
        }

        val inputTitulo = EditText(requireContext()).apply {
            hint = "Título"
        }
        val inputDescripcion = EditText(requireContext()).apply {
            hint = "Descripción"
        }
        val spinnerPrioridad = Spinner(requireContext()).apply {
            adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                listOf("baja", "media", "alta")
            )
        }

        val fechaSeleccionada = TextView(requireContext()).apply {
            text = "Seleccionar fecha"
            setPadding(0, 20, 0, 20)
            setOnClickListener {
                val calendario = Calendar.getInstance()
                DatePickerDialog(requireContext(), { _, año, mes, dia ->
                    val calendar = Calendar.getInstance()
                    calendar.set(año, mes, dia, 0, 0, 0)
                    this.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)
                    this.tag = calendar.timeInMillis
                },
                    calendario.get(Calendar.YEAR),
                    calendario.get(Calendar.MONTH),
                    calendario.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }

        layout.addView(inputTitulo)
        layout.addView(inputDescripcion)
        layout.addView(spinnerPrioridad)
        layout.addView(fechaSeleccionada)

        AlertDialog.Builder(requireContext())
            .setTitle("Nueva Tarea")
            .setView(layout)
            .setPositiveButton("Guardar") { dialog, _ ->
                val titulo = inputTitulo.text.toString()
                val descripcion = inputDescripcion.text.toString()
                val prioridad = spinnerPrioridad.selectedItem.toString()
                val id = firestore.collection("tareas").document().id
                val fecha = fechaSeleccionada.tag as? Long

                if (titulo.isBlank() || descripcion.isBlank() || fecha == null) {
                    Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                val nuevaTarea = Tarea(
                    ID = id,
                    titulo = titulo,
                    descripcion = descripcion,
                    prioridad = prioridad,
                    fecha = fecha,
                    hecha = false
                )

                firestore.collection("tareas")
                    .document(id)
                    .set(nuevaTarea)
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "Tarea guardada", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(requireContext(), "Error al guardar", Toast.LENGTH_SHORT).show()
                    }

                dialog.dismiss()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}
