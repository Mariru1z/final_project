package com.example.agenda_proyecto.domain.usecase

import com.example.agenda_proyecto.domain.model.Tarea
import com.example.agenda_proyecto.domain.repository.TareaRepository
import kotlinx.coroutines.flow.Flow

class ObtenerTareasUseCase(private val repository: TareaRepository) {
    operator fun invoke(): Flow<List<Tarea>> = repository.obtenerTareas()
}
