package com.example.fitnesstrackerapp.uiViewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstrackerapp.basedatos.dao.FechaDao
import com.example.fitnesstrackerapp.basedatos.entidades.FechaDia
import com.example.fitnesstrackerapp.basedatos.entidades.UsuarioInfo
import com.example.fitnesstrackerapp.basedatos.repositorio.FechaRepositorio
import com.example.fitnesstrackerapp.basedatos.repositorio.UsuarioRepositorio
import com.example.fitnesstrackerapp.modelmapper.ModelMapperConfig
import com.example.fitnesstrackerapp.objetos.login.UsuarioVerificar
import com.example.fitnesstrackerapp.objetos.response.ResponseLogin
import com.example.fitnesstrackerapp.repositorio.RepositorioRetrofit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dao: UsuarioRepositorio,
    private val fDao:FechaRepositorio,
    private val eventosViewModel: EventosViewModel = EventosViewModel()
) :ViewModel()
{

    @RequiresApi(Build.VERSION_CODES.O)
    private val repositorio: RepositorioRetrofit = RepositorioRetrofit()

    private val _userVerify = MutableStateFlow(UsuarioVerificar())

    private val _usuarioInfo = MutableStateFlow(ResponseLogin())
    val usuarioInfo:StateFlow<ResponseLogin> get() = _usuarioInfo.asStateFlow()

    init {
        viewModelScope.launch (Dispatchers.IO){
            dao.borrarUsuario()
            fDao.borrar()
            fDao.insertar(FechaDia(1,LocalDate.now().toString(),false))
        }
    }

    fun login(email:String, pass:String){
        viewModelScope.launch (Dispatchers.IO){
            eventosViewModel.setState(EventosUIState.Cargando)
            delay(4000)
            _userVerify.value.email = email
            _userVerify.value.contrasena = pass

            try{
                _usuarioInfo.value = repositorio.verificar(_userVerify.value)
                if(_usuarioInfo.value.success){
                    var usuarioInfo = ModelMapperConfig.modelMapper.map(_usuarioInfo.value.data,UsuarioInfo::class.java)
                    usuarioInfo.token = _usuarioInfo.value.data.token?:""
                    usuarioInfo.email = _userVerify.value.email

                    dao.insertarUsuario(usuarioInfo)
                }else{
                    eventosViewModel.setState(EventosUIState.Error("Las credenciales no son validas"))
                }
            }catch (e:Exception){
                eventosViewModel.setState(EventosUIState.Error(e.toString()))
                Log.i("error",e.toString())
                _usuarioInfo.value = ResponseLogin()
            }
        }
    }

    fun setUser(){
        _userVerify.value = UsuarioVerificar()
        _usuarioInfo.value = ResponseLogin()
    }
}