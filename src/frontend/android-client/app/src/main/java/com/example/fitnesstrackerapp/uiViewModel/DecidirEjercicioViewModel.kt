package com.example.fitnesstrackerapp.uiViewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstrackerapp.basedatos.entidades.Ejercicio
import com.example.fitnesstrackerapp.basedatos.repositorio.EjercicioRepositorio
import com.example.fitnesstrackerapp.basedatos.repositorio.FechaRepositorio
import com.example.fitnesstrackerapp.basedatos.repositorio.UsuarioRepositorio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DecidirEjercicioViewModel @Inject constructor(
    private val edao: EjercicioRepositorio,
    private val fDao: FechaRepositorio,
    private val eventosViewModel: EventosViewModel = EventosViewModel()
): ViewModel(){

    private val _done = MutableStateFlow(false)
    val done:StateFlow<Boolean> get() = _done.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun introducirEjercicio(nombre:String, duracion:String, kcal:String){
        viewModelScope.launch (Dispatchers.IO){
            try {
                eventosViewModel.setState(EventosUIState.Cargando)
                var ejecicio2 = Ejercicio()
                val fecha = fDao.getFecha()

                ejecicio2.nombre = nombre
                ejecicio2.minutos = duracion.toInt()
                ejecicio2.Kcal = kcal.toInt()
                ejecicio2.fecha = fecha

                edao.insert(ejecicio2)
                eventosViewModel.setState(EventosUIState.Done)
                _done.value = true
            }catch (e:Exception){
                eventosViewModel.setState(EventosUIState.Error("No se ha podido meter el ejericio"))
            }
        }
    }


}