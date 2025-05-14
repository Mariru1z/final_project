package com.example.agenda_app_kotlin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TareaViewModel : ViewModel() {

    private val _titulo = MutableLiveData<String>()
    val titulo: LiveData<String> = _titulo

    private val _descripcion = MutableLiveData<String>()
    val descripcion: LiveData<String> = _descripcion

    private val _prioridad = MutableLiveData<String>()
    val prioridad: LiveData<String> = _prioridad

    private val _fecha = MutableLiveData<Long>()
    val fecha: LiveData<Long> = _fecha

    fun setTitulo(valor: String) { _titulo.value = valor }
    fun setDescripcion(valor: String) { _descripcion.value = valor }
    fun setPrioridad(valor: String) { _prioridad.value = valor }
    fun setFecha(valor: Long) { _fecha.value = valor }
}
