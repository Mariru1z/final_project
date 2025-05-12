package com.example.simpleagenda.data.repository

import com.example.simpleagenda.data.database.TaskDao
import com.example.simpleagenda.data.database.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) {
    fun getTasks(): Flow<List<TaskEntity>> = dao.getAll()
    suspend fun addTask(task: TaskEntity) = dao.insert(task)
    suspend fun deleteTask(task: TaskEntity) = dao.delete(task)
}
