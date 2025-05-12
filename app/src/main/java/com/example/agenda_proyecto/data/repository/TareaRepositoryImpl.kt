package com.example.agenda_proyecto.data.repository

import com.example.agenda_proyecto.data.local.TareaDao
import com.example.agenda_proyecto.domain.model.Tarea
import com.example.agenda_proyecto.domain.repository.TareaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TareaRepositoryImpl(
    private val tareaDao: TareaDao
) : TareaRepository {

    override fun obtenerTareas(): Flow<List<Tarea>> {
        return tareaDao.getAll().map { listaEntity ->
            listaEntity.map { it.toDomain() }
        }
    }

    override suspend fun agregarTarea(tarea: Tarea) {
        tareaDao.insert(tarea.toEntity())
    }

    override suspend fun eliminarTarea(tarea: Tarea) {
        tareaDao.delete(tarea.toEntity())
    }

    override suspend fun getById(id: Int): Tarea? {
        return tareaDao.getById(id)?.toDomain()
    }
}
