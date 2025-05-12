package com.example.simpleagenda.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.example.simpleagenda.data.database.AppDatabase
import com.example.simpleagenda.data.database.TaskEntity
import com.example.simpleagenda.data.repository.TaskRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TaskRepository
    val tasks: LiveData<List<TaskEntity>>

    init {
        val dao = AppDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(dao)
        tasks = repository.getTasks().asLiveData()
    }

    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch { repository.deleteTask(task) }
    }
}
