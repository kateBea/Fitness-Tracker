package com.example.fitnesstrackerapp.uiViewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.fitnesstrackerapp.basedatos.repositorio.UsuarioRepositorio
import com.example.fitnesstrackerapp.repositorio.RepositorioRetrofit
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val dao: UsuarioRepositorio,
    private val eventosViewModel: EventosViewModel = EventosViewModel()
): ViewModel(){

    @RequiresApi(Build.VERSION_CODES.O)
    private val repositorio: RepositorioRetrofit = RepositorioRetrofit()



}