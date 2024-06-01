package com.example.fitnesstrackerapp.apiservicio

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.fitnesstrackerapp.objetos.login.UsuarioVerificar
import com.example.fitnesstrackerapp.objetos.request.UsuarioRequest
import com.example.fitnesstrackerapp.objetos.response.ResponseLogin
import com.example.fitnesstrackerapp.objetos.usuario.DatosUsuario
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

interface ApiServicio {
    @POST("login")
    suspend fun hacerLogin(@Body user:UsuarioVerificar): ResponseLogin

    @POST("getdatosusuario")
    suspend fun getDatosUsuario(@Body email:String):DatosUsuario

    @PUT("modificardatosusuario")
    suspend fun actualizarUsuario(@Body usuarioRequest: UsuarioRequest)

}

@RequiresApi(Build.VERSION_CODES.O)
fun getRetrofitClient():Retrofit{

    val gsonDateTime = GsonBuilder().registerTypeAdapter(
        LocalDateTime::class.java,
        JsonDeserializer { json, _, _ ->
            LocalDateTime.parse(json.asJsonPrimitive.asString,
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS"))
        }
    ).create()

    return Retrofit.Builder().baseUrl("http://HOST_IP_ADDRESS:HOST_PORT/Base/")
        .addConverterFactory(GsonConverterFactory.create(gsonDateTime))
        .build()
}