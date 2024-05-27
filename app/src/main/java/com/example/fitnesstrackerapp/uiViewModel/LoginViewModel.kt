package com.example.fitnesstrackerapp.uiViewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstrackerapp.basedatos.dao.UsuarioDao
import com.example.fitnesstrackerapp.basedatos.entidades.UsuarioInfo
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
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dao: UsuarioRepositorio,
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
                if(_usuarioInfo.value.email != null){
                    val modelMapper = ModelMapperConfig.modelMapper
                    val usuarioInfo:UsuarioInfo = modelMapper.map(_usuarioInfo.value,UsuarioInfo::class.java)
                    dao.insertarUsuario(usuarioInfo)
                }else{
                    eventosViewModel.setState(EventosUIState.Error(_usuarioInfo.value.responseDescription))
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