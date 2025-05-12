package com.example.agenda_proyecto.data.repository

import com.example.agenda_proyecto.data.database.TaskDao
import com.example.agenda_proyecto.data.database.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    fun getTasks(): Flow<List<TaskEntity>> = taskDao.getAllTasks()

    suspend fun addTask(task: TaskEntity) {
        taskDao.insertTask(task)
    }

    suspend fun deleteTask(task: TaskEntity) {
        taskDao.deleteTask(task)
    }
}