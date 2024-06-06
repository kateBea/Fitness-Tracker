package com.example.fitnesstrackerapp.uiViewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstrackerapp.basedatos.entidades.UsuarioInfo
import com.example.fitnesstrackerapp.basedatos.repositorio.UsuarioRepositorio
import com.example.fitnesstrackerapp.objetos.response.Sexo
import com.example.fitnesstrackerapp.objetos.usuario.Usuario
import com.example.fitnesstrackerapp.repositorio.RepositorioRetrofit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class InformacionVistaViewModel @Inject constructor(
    private val dao: UsuarioRepositorio,
    private val eventosViewModel: EventosViewModel = EventosViewModel()
): ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val repositorio: RepositorioRetrofit = RepositorioRetrofit()

    private val _kcal  = MutableStateFlow(0f)
    val kcal:StateFlow<Float> get() = _kcal.asStateFlow()

    private val _proteinas  = MutableStateFlow(0f)
    val proteinas:StateFlow<Float> get() = _proteinas.asStateFlow()

    private val _grasas  = MutableStateFlow(0f)
    val grasas:StateFlow<Float> get() = _grasas.asStateFlow()

    private val _carbohidratos  = MutableStateFlow(0f)
    val carbohidratos:StateFlow<Float> get() = _carbohidratos.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    private val _usuario = MutableStateFlow(UsuarioInfo())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            eventosViewModel.setState(EventosUIState.Cargando)
            _usuario.value = dao.getUsuario()
            calcularKCal()
            eventosViewModel.setState(EventosUIState.Done)
        }
    }

    fun calcularKCal(){
        /*
        Para hombres: RMB = (10 x peso en kg) + (6.25 x altura en cm) – (5 x edad en años) + 5.
        Para mujeres: RMB = (10 x peso en kg) + (6.25 x altura en cm) – (5 x edad en años) – 161.
         */
        val hombreMujer = if(_usuario.value.sexo == Sexo.Mujer) -161 else 5
        val anios = Period.between(LocalDate.parse(_usuario.value.fechaNacimiento), LocalDate.now()).years

        val rmb = (10 * _usuario.value.peso) + (6.25 * _usuario.value.altura) - (5 * anios)  + hombreMujer

        _kcal.value = rmb.toFloat()
        _proteinas.value = _kcal.value * 0.4f / 4
        _grasas.value = _kcal.value * 0.3f / 9
        _carbohidratos.value = _kcal.value * 0.3f / 4

    }

}