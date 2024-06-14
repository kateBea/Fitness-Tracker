package com.example.fitnesstrackerapp.uiViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

/**
 * ViewModel para gestionar el estado de la interfaz de usuario relacionado con los eventos.
 * Esta clase está anotada con @HiltViewModel para habilitar la inyección de dependencias usando Hilt.
 */
@HiltViewModel
class EventosViewModel @Inject constructor() : ViewModel() {

    /**
     * Estado mutable de la interfaz de usuario, inicializado con el estado Done.
     */
    private var _uiState = MutableStateFlow<EventosUIState>(EventosUIState.Done)

    /**
     * Estado inmutable de la interfaz de usuario expuesto para la observación.
     */
    val uiState: StateFlow<EventosUIState> get() = _uiState.asStateFlow()

    /**
     * Función para actualizar el estado de la interfaz de usuario.
     * Utiliza viewModelScope para lanzar una coroutine que actualiza el valor del estado.
     *
     * @param eventosUIState El nuevo estado a establecer.
     */
    fun setState(eventosUIState: EventosUIState) = viewModelScope.launch {
        _uiState.value = eventosUIState
    }
}

/**
 * Clase sellada que representa los posibles estados de la interfaz de usuario relacionados con los eventos.
 */
sealed class EventosUIState {

    /**
     * Estado de error, contiene un mensaje de texto que describe el error.
     *
     * @property texto El mensaje de error.
     */
    data class Error(val texto: String) : EventosUIState()

    /**
     * Estado de carga, indica que se está cargando información.
     */
    data object Cargando : EventosUIState()

    /**
     * Estado finalizado, indica que no hay ninguna operación en curso.
     */
    data object Done : EventosUIState()
}