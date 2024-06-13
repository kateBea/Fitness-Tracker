package com.example.fitnesstrackerapp.uiViewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstrackerapp.basedatos.repositorio.UsuarioRepositorio
import com.example.fitnesstrackerapp.objetos.request.RegisterRequest
import com.example.fitnesstrackerapp.repositorio.RepositorioRetrofit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val dao: UsuarioRepositorio,
    private val eventosViewModel: EventosViewModel = EventosViewModel()
): ViewModel(){

    @RequiresApi(Build.VERSION_CODES.O)
    private val repositorio: RepositorioRetrofit = RepositorioRetrofit()

    private val _done = MutableStateFlow(false)
    val done:StateFlow<Boolean> get() = _done.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun hacerRegister(user2:String, email:String, contra:String){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                eventosViewModel.setState(EventosUIState.Cargando)
                var user = RegisterRequest()
                user.email = email
                user.password = contra
                user.username = user2

                val hecho = repositorio.register(user)

                if(hecho.valido){
                    _done.value = true
                    eventosViewModel.setState(EventosUIState.Done)
                }else{
                    eventosViewModel.setState(EventosUIState.Error("No se ha insertado bien el usuario"))
                }
                eventosViewModel.setState(EventosUIState.Done)
            }catch(e:Exception){
                eventosViewModel.setState(EventosUIState.Error("No se ha insertado bien el usuario"))
            }
        }
    }


}