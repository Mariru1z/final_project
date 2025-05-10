package com.example.agenda_proyecto.domain.repository

import com.example.agenda_proyecto.domain.model.Tarea
import kotlinx.coroutines.flow.Flow

interface TareaRepository {
    fun obtenerTareas(): Flow<List<Tarea>>
    suspend fun agregarTarea(tarea: Tarea)
    suspend fun eliminarTarea(tarea: Tarea)
    suspend fun getById(id: Int): Tarea?
}

