package com.example.fitnesstrackerapp.uiViewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstrackerapp.basedatos.entidades.Comida
import com.example.fitnesstrackerapp.basedatos.repositorio.FechaRepositorio
import com.example.fitnesstrackerapp.basedatos.repositorio.UsuarioRepositorio
import com.example.fitnesstrackerapp.objetos.response.responseTodosAlimentos.ComidaTodos
import com.example.fitnesstrackerapp.repositorio.RepositorioRetrofit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class AlimentosUserViewModel @Inject constructor(
    private val dao: UsuarioRepositorio,
    private val fDao: FechaRepositorio,
    private val eventosViewModel: EventosViewModel = EventosViewModel()
): ViewModel(){

    private val _comidas = MutableStateFlow<List<ComidaTodos>>(listOf())
    val comidas:StateFlow<List<ComidaTodos>> get() = _comidas.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    private val repositorio: RepositorioRetrofit = RepositorioRetrofit()

    init {
        viewModelScope.launch (Dispatchers.IO){
            try {
                eventosViewModel.setState(EventosUIState.Cargando)
                val token = dao.getToken()?:""

                val respuesta = repositorio.getAlimentosTodos(token)
                _comidas.value = respuesta.comidas

                eventosViewModel.setState(EventosUIState.Done)
            }catch (e:Exception){
                eventosViewModel.setState(EventosUIState.Error("No se han podido mostrar los alimentos"))
            }

        }
    }

}