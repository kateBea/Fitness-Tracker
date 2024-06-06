package com.example.fitnesstrackerapp.apiservicio

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.fitnesstrackerapp.objetos.login.UsuarioVerificar
import com.example.fitnesstrackerapp.objetos.request.RutinaRequest
import com.example.fitnesstrackerapp.objetos.request.UsuarioRequest
import com.example.fitnesstrackerapp.objetos.response.AlimentosResponse
import com.example.fitnesstrackerapp.objetos.response.ResponseInsertar
import com.example.fitnesstrackerapp.objetos.response.ResponseLogin
import com.example.fitnesstrackerapp.objetos.response.RutinasResponse
import com.example.fitnesstrackerapp.objetos.response.responseAlimentos.ResponseAlimentos
import com.example.fitnesstrackerapp.objetos.usuario.DatosUsuario
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

interface ApiServicio {
    @POST("client/Login")
    suspend fun hacerLogin(@Body user:UsuarioVerificar): ResponseLogin

    @GET("client/getdatosusuario")
    suspend fun getDatosUsuario(@Header("Authorization") token:String):DatosUsuario

    @PUT("client/modificardatosusuario")
    suspend fun actualizarUsuario(@Header("Authorization") token:String,@Body usuarioRequest: UsuarioRequest)

    @GET("client/GetListRutinasUsuario")
    suspend fun getRutinasUsuario(@Header("Authorization") token:String, @Query("FetchAll") fetch:Boolean, @Query("FechaInicio") fechaInicio:String, @Query("FechaFin") fechaFinal:String):AlimentosResponse

    @GET("alimentos/BuscarPorDescripcion")
    suspend fun buscarAlimentos(@Header("Authorization") token:String, @Query("prompt") prompt:String):ResponseAlimentos

    @POST("client/RegistrarRutina")
    suspend fun insertarRutina(@Header("Authorization") token:String, @Body rutinasResponse: RutinaRequest): ResponseInsertar

    @PUT("client/ModificarRutina")
    suspend fun modificarDieta(@Header("Authorization") token:String, @Body rutinasResponse: RutinasResponse)

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

    // Create an interceptor for logging HTTP requests and responses
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    // Configure OkHttpClient with the logging interceptor
    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    return Retrofit.Builder().baseUrl("http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/")
        .addConverterFactory(GsonConverterFactory.create(gsonDateTime))
        .client(client)
        .build()
}
//http://192.168.1.132:50598/api/fitnesstracker/

//Cambiar puerto cada vez que entro en el docker
//cambiar parametro despues de api