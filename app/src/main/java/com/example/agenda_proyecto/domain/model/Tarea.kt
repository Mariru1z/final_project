package com.example.agenda_proyecto.domain.model

data class Tarea(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val fecha: Long,
    val completada: Boolean
)
