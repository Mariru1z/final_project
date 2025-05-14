package com.example.agenda_app_kotlin.Bottom_Nav_Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda_app_kotlin.R
import com.example.agenda_app_kotlin.model.Tarea
import com.google.firebase.firestore.FirebaseFirestore

class FragmentTareas : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TareasAdapter
    private val tareasList = mutableListOf<Tarea>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tareas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerTareas)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = TareasAdapter(tareasList)
        recyclerView.adapter = adapter

        cargarTareas()
    }

    private fun cargarTareas() {
        FirebaseFirestore.getInstance().collection("tareas")
            .get()
            .addOnSuccessListener { documentos ->
                tareasList.clear()
                for (doc in documentos) {
                    val tarea = doc.toObject(Tarea::class.java)
                    tareasList.add(tarea)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Error al cargar tareas", Toast.LENGTH_SHORT).show()
            }
    }
}
