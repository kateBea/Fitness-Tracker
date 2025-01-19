package com.example.fitnesstrackerapp.uiViewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstrackerapp.basedatos.entidades.Ejercicio
import com.example.fitnesstrackerapp.basedatos.repositorio.EjercicioRepositorio
import com.example.fitnesstrackerapp.basedatos.repositorio.FechaRepositorio
import com.example.fitnesstrackerapp.basedatos.repositorio.UsuarioRepositorio
import com.example.fitnesstrackerapp.repositorio.RepositorioRetrofit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EjerciciosTodosViewModel @Inject constructor(
    private val dao: UsuarioRepositorio,
    private val eDao: EjercicioRepositorio,
    private val eventosViewModel: EventosViewModel = EventosViewModel()
) : ViewModel(){

    @RequiresApi(Build.VERSION_CODES.O)
    private val repositorio: RepositorioRetrofit = RepositorioRetrofit()

    private val _ejercicios = MutableStateFlow<List<Ejercicio>>(listOf())
    val ejercicios:StateFlow<List<Ejercicio>> get() = _ejercicios.asStateFlow()

    init {
        viewModelScope.launch (Dispatchers.IO){
            try {
                eventosViewModel.setState(EventosUIState.Cargando)

                val respuesta = eDao.getAll()
                _ejercicios.value = respuesta

                eventosViewModel.setState(EventosUIState.Done)
            }catch (e:Exception){
                eventosViewModel.setState(EventosUIState.Error("No se han cargado correctamente los ejercicios"))
            }
        }
    }

}