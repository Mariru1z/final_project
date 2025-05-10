package com.example.agenda_proyecto.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agenda_proyecto.domain.model.Tarea
import com.example.agenda_proyecto.domain.usecase.ObtenerTareasUseCase
import com.example.agenda_proyecto.utils.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TareaViewModel(
    private val obtenerTareasUseCase: ObtenerTareasUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<List<Tarea>>>(UIState.Loading)
    val uiState: StateFlow<UIState<List<Tarea>>> = _uiState

    fun obtenerTareas() {
        viewModelScope.launch {
            try {
                val tareas = obtenerTareasUseCase()
                _uiState.value = UIState.Success(tareas)
            } catch (e: Exception) {
                _uiState.value = UIState.Error("Error al cargar tareas")
            }
        }
    }
}
