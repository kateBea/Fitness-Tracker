package com.example.fitnesstrackerapp.uiViewModel

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstrackerapp.basedatos.entidades.UsuarioInfo
import com.example.fitnesstrackerapp.basedatos.repositorio.UsuarioRepositorio
import com.example.fitnesstrackerapp.clases.InfoMenu.InfoMenu
import com.example.fitnesstrackerapp.clases.InfoMenu.InfoResultado
import com.example.fitnesstrackerapp.modelmapper.ModelMapperConfig
import com.example.fitnesstrackerapp.objetos.request.UsuarioRequest
import com.example.fitnesstrackerapp.objetos.response.Sexo
import com.example.fitnesstrackerapp.repositorio.RepositorioRetrofit
import com.example.fitnesstrackerapp.utilidades.valores
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class PantallaPrincipalViewModel @Inject constructor(
    private val dao: UsuarioRepositorio,
    private val eventosViewModel: EventosViewModel = EventosViewModel()
): ViewModel(){

    @RequiresApi(Build.VERSION_CODES.O)
    private val repositorio: RepositorioRetrofit = RepositorioRetrofit()

    private val _menuDatosUsuario = MutableStateFlow<MutableList<InfoMenu>>(mutableListOf())
    val menuDatosUsuario:StateFlow<MutableList<InfoMenu>> get() = _menuDatosUsuario.asStateFlow()

    private val _usuario = MutableStateFlow<UsuarioInfo>(UsuarioInfo())

    private val _datos = MutableStateFlow<MutableList<InfoResultado>>(mutableListOf())
    val datos:StateFlow<MutableList<InfoResultado>> get() = _datos.asStateFlow()

    init {
        viewModelScope.launch (Dispatchers.IO){
            eventosViewModel.setState(EventosUIState.Cargando)
            _usuario.value = dao.getUsuario()

            try{
                val token = dao.getToken()?:""
                val email = dao.getEmail()?:""
                val datosUser = repositorio.getDatosUsuario(token)
                Log.i("datosUser","${datosUser}")


                val modelMapper = ModelMapperConfig.modelMapper
                _usuario.value = modelMapper.map(datosUser.usuario, UsuarioInfo::class.java)
                _usuario.value.nombreUsuario = datosUser.usuario.nombreUsuario
                _usuario.value.fechaNacimiento = datosUser.usuario.fechaNacimiento
                _usuario.value.fechaRegistro = datosUser.usuario.fechaRegistro
                _usuario.value.fechaUltimaModificacion = LocalDate.now().toString()
                _usuario.value.email = email
                _usuario.value.token = token

                dao.actualizarUsuario(_usuario.value)

                actualizarDatos()
                setInfo()
                eventosViewModel.setState(EventosUIState.Done)
            }catch (e:Exception){
                Log.i("Error","${e.message}")
                eventosViewModel.setState(EventosUIState.Error(e.message.toString()))
            }
        }
    }

    fun setInfo(){
        val datosPersona = valores
        datosPersona.get(0).info = calcularMetabolismo().toString()
        datosPersona.get(1).info = calcularIMC()
        datosPersona.get(2).info = requerimientoAgua().toString()
        _datos.value = datosPersona
        Log.i("setInfo","${_datos.value}")
    }

    fun calcularMetabolismo():Float{
        val esHombre = if(_usuario.value.sexo == Sexo.Hombre) 5 else -161
        val TMB = (10 * _usuario.value.peso) + (6.25 * _usuario.value.altura) - (5 * Period.between(LocalDate.parse(_usuario.value.fechaNacimiento),LocalDate.now()).years) + esHombre
        return TMB.toFloat()
    }

    @SuppressLint("DefaultLocale")
    fun calcularIMC():String{
        val metros = _usuario.value.altura / 100
        return String.format("%.2f", _usuario.value.peso / (metros * metros))
    }

    fun requerimientoAgua():Float{
        return 0.033f * _usuario.value.peso
    }

    fun cambiarAltura(valor:String){
        viewModelScope.launch (Dispatchers.IO){
            eventosViewModel.setState(EventosUIState.Cargando)
            val altura = valor.toFloat()
            _usuario.value.altura = altura
            dao.actualizarUsuario(_usuario.value)
            setInfo()
            actualizarDatos()

            val usuario = ModelMapperConfig.modelMapper.map(_usuario.value, UsuarioRequest::class.java)
            Log.i("usuario","${usuario}")
            repositorio.actualizarUsuario(_usuario.value.token,usuario)

            eventosViewModel.setState(EventosUIState.Done)
        }
    }

    fun cambiarPeso(valor:String){
        viewModelScope.launch (Dispatchers.IO){
            eventosViewModel.setState(EventosUIState.Cargando)
            val peso = valor.toFloat()
            _usuario.value.peso = peso
            dao.actualizarUsuario(_usuario.value)
            setInfo()
            actualizarDatos()

            val usuario = ModelMapperConfig.modelMapper.map(_usuario.value, UsuarioRequest::class.java)
            repositorio.actualizarUsuario(_usuario.value.token,usuario)

            eventosViewModel.setState(EventosUIState.Done)
        }
    }

    fun cambiarSexo(valor:String){
        viewModelScope.launch (Dispatchers.IO){
            eventosViewModel.setState(EventosUIState.Cargando)
            val sexo = Sexo.valueOf(valor)
            _usuario.value.sexo = sexo
            dao.actualizarUsuario(_usuario.value)
            setInfo()
            actualizarDatos()

            val usuario = ModelMapperConfig.modelMapper.map(_usuario.value, UsuarioRequest::class.java)
            repositorio.actualizarUsuario(_usuario.value.token,usuario)

            eventosViewModel.setState(EventosUIState.Done)
        }
    }

    fun actualizarDatos(){
        val datos: MutableList<InfoMenu> = mutableListOf()
        datos.add(InfoMenu("Altura (cm)",_usuario.value.altura.toString(), Acciones.CambiarAltura))
        datos.add(InfoMenu("Peso (K)",_usuario.value.peso.toString(), Acciones.CambiarPeso))
        datos.add(InfoMenu("Sexo",_usuario.value.sexo.toString(), Acciones.CambiarSexo))
        datos.add(InfoMenu("Edad",Period.between(LocalDate.parse(_usuario.value.fechaNacimiento), LocalDate.now()).years.toString(), Acciones.CambiarEdad))

        _menuDatosUsuario.value = datos
    }

}

enum class Acciones(){
    CambiarPeso,
    CambiarAltura,
    CambiarSexo,
    CambiarEdad
}