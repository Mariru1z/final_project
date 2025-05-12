package com.example.agenda_proyecto.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.agenda_proyecto.domain.usecase.ObtenerTareasUseCase

class TareaViewModelFactory(
    private val obtenerTareasUseCase: ObtenerTareasUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TareaViewModel::class.java)) {
            return TareaViewModel(obtenerTareasUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
