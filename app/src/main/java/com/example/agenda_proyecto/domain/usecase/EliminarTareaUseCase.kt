package com.example.agenda_proyecto.domain.usecase

import com.example.agenda_proyecto.domain.model.Tarea
import com.example.agenda_proyecto.domain.repository.TareaRepository

class EliminarTareaUseCase(private val repository: TareaRepository) {
    suspend operator fun invoke(tarea: Tarea) {
        repository.eliminarTarea(tarea)
    }
}
