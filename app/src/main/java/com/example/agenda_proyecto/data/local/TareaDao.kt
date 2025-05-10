package com.example.agenda_proyecto.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TareaDao {

    @Query("SELECT * FROM tareas")
    fun getAll(): Flow<List<TareaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tarea: TareaEntity)

    @Update
    suspend fun update(tarea: TareaEntity)

    @Delete
    suspend fun delete(tarea: TareaEntity)

    @Query("SELECT * FROM tareas WHERE id = :id")
    suspend fun getById(id: Int): TareaEntity?
}
