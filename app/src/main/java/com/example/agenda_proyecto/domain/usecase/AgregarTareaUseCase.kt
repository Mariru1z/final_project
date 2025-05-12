package com.example.agenda_proyecto.domain.usecase

import com.example.agenda_proyecto.domain.model.Tarea
import com.example.agenda_proyecto.domain.repository.TareaRepository

class AgregarTareaUseCase(private val repository: TareaRepository) {
    suspend operator fun invoke(tarea: Tarea) {
        repository.agregarTarea(tarea)
    }
}
