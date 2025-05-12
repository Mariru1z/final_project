package com.example.agenda_proyecto.data.repository

import com.example.agenda_proyecto.domain.model.Tarea
import com.example.agenda_proyecto.data.local.TareaEntity

fun TareaEntity.toDomain(): Tarea = Tarea(id, titulo, descripcion, fecha, completada)

fun Tarea.toEntity(): TareaEntity = TareaEntity(id, titulo, descripcion, fecha, completada)
