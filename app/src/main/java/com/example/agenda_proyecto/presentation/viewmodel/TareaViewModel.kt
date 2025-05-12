package com.example.agenda_proyecto.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agenda_proyecto.domain.model.Tarea
import com.example.agenda_proyecto.domain.usecase.ObtenerTareasUseCase
import com.example.agenda_proyecto.utils.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest

class TareaViewModel(
    private val obtenerTareasUseCase: ObtenerTareasUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<List<Tarea>>>(UIState.Loading)
    val uiState: StateFlow<UIState<List<Tarea>>> = _uiState

    fun obtenerTareas() {
        viewModelScope.launch {
            obtenerTareasUseCase()
                .catch { e ->
                    _uiState.value = UIState.Error("Error al cargar tareas: ${e.message}")
                }
                .collectLatest { tareas ->
                    _uiState.value = UIState.Success(tareas)
                }
        }
    }
}

