package com.example.agenda_proyecto.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.agenda_proyecto.R
import com.example.agenda_proyecto.data.local.DatabaseProvider
import com.example.agenda_proyecto.data.repository.TareaRepositoryImpl
import com.example.agenda_proyecto.domain.usecase.ObtenerTareasUseCase
import com.example.agenda_proyecto.presentation.viewmodel.TareaViewModel
import com.example.agenda_proyecto.presentation.viewmodel.TareaViewModelFactory
import com.example.agenda_proyecto.utils.UIState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListaTareasFragment : Fragment() {

    private val tareaViewModel: TareaViewModel by viewModels {
        TareaViewModelFactory(
            ObtenerTareasUseCase(
                TareaRepositoryImpl(
                    DatabaseProvider.getDatabase(requireContext()).tareaDao()
                )
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lista_tareas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tareasTextView = view.findViewById<TextView>(R.id.tareas_text)

        tareaViewModel.obtenerTareas()

        lifecycleScope.launch {
            tareaViewModel.uiState.collectLatest { state ->
                when (state) {
                    is UIState.Loading -> tareasTextView.text = "Cargando tareas..."
                    is UIState.Success -> tareasTextView.text =
                        state.data.joinToString("\n") { it.titulo }
                    is UIState.Error -> tareasTextView.text = state.message
                }
            }
        }
    }
}
