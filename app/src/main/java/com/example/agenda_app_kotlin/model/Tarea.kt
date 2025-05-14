package com.example.agenda_app_kotlin.model

data class Tarea(
    val ID: String = "",
    val titulo: String = "",
    val descripcion: String = "",
    val prioridad: String = "",
    val fecha: Long = 0L,
    val hecha: Boolean = false
)