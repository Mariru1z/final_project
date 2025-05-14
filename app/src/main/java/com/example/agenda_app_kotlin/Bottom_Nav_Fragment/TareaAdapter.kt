package com.example.agenda_app_kotlin.Bottom_Nav_Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda_app_kotlin.R
import com.example.agenda_app_kotlin.model.Tarea

class TareasAdapter(private val tareas: List<Tarea>) : RecyclerView.Adapter<TareasAdapter.TareaViewHolder>() {

    inner class TareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val prioridad: TextView = itemView.findViewById(R.id.txtPrioridad)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tarea, parent, false)
        return TareaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = tareas[position]
        holder.titulo.text = tarea.titulo
        holder.prioridad.text = tarea.prioridad
    }

    override fun getItemCount(): Int = tareas.size
}
