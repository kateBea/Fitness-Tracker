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
import java.time.LocalDate
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
    fun hacerRegister(user1:String, email:String, contra:String){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                eventosViewModel.setState(EventosUIState.Cargando)
                var user2 = RegisterRequest()
                user2.email = email
                user2.password = contra
                user2.username = user1
                user2.birthday = LocalDate.now().toString()

                val hecho = repositorio.register(user2)

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