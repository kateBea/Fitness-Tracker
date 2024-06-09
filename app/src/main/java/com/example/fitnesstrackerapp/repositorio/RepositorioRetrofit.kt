package com.example.fitnesstrackerapp.repositorio

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.fitnesstrackerapp.apiservicio.ApiServicio
import com.example.fitnesstrackerapp.apiservicio.getRetrofitClient
import com.example.fitnesstrackerapp.objetos.login.UsuarioVerificar
import com.example.fitnesstrackerapp.objetos.request.RegisterRequest
import com.example.fitnesstrackerapp.objetos.request.RutinaRequest
import com.example.fitnesstrackerapp.objetos.request.UsuarioRequest
import com.example.fitnesstrackerapp.objetos.response.AlimentosResponse
import com.example.fitnesstrackerapp.objetos.response.ResponseInsertar
import com.example.fitnesstrackerapp.objetos.response.ResponseLogin
import com.example.fitnesstrackerapp.objetos.response.RutinasResponse
import com.example.fitnesstrackerapp.objetos.response.responseAlimentos.ResponseAlimentos
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

    suspend fun getDatosUsuario(token:String):DatosUsuario{
        return apiServicio.create(ApiServicio::class.java).getDatosUsuario("Bearer $token")
    }

    suspend fun actualizarUsuario(token:String,usuarioRequest: UsuarioRequest){
        return apiServicio.create(ApiServicio::class.java).actualizarUsuario("Bearer $token",usuarioRequest)
    }

    suspend fun getListasUsuario(token:String,fechaInicio:String,fechaFin:String): AlimentosResponse {
        return apiServicio.create(ApiServicio::class.java).getRutinasUsuario("Bearer $token",false,fechaInicio,fechaFin)
    }

    suspend fun buscarAlimentos(token: String,cadena:String):ResponseAlimentos{
        return apiServicio.create(ApiServicio::class.java).buscarAlimentos("Bearer $token",cadena)
    }

    suspend fun insertarRutina(token: String,rutina:RutinaRequest): ResponseInsertar {
        return apiServicio.create(ApiServicio::class.java).insertarRutina("Bearer $token",rutina)
    }

    suspend fun actualizarRutina(token: String,rutina:RutinasResponse){
        return apiServicio.create(ApiServicio::class.java).modificarDieta("Bearer $token",rutina)
    }

    suspend fun register(user:RegisterRequest){
        return apiServicio.create(ApiServicio::class.java).register(user)
    }

}