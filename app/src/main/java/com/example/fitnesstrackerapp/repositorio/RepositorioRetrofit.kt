package com.example.fitnesstrackerapp.repositorio

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.fitnesstrackerapp.apiservicio.ApiServicio
import com.example.fitnesstrackerapp.apiservicio.getRetrofitClient
import com.example.fitnesstrackerapp.objetos.login.UsuarioVerificar
import com.example.fitnesstrackerapp.objetos.response.ResponseLogin
import com.example.fitnesstrackerapp.objetos.usuario.DatosUsuario
import retrofit2.Retrofit
import retrofit2.create

@RequiresApi(Build.VERSION_CODES.O)
class RepositorioRetrofit (
    private val apiServicio: Retrofit = getRetrofitClient()
){
    suspend fun verificar(user:UsuarioVerificar):ResponseLogin{
        return apiServicio.create(ApiServicio::class.java).hacerLogin(user)
    }

    suspend fun getDatosUsuario(email:String):DatosUsuario{
        return apiServicio.create(ApiServicio::class.java).getDatosUsuario(email)
    }

}