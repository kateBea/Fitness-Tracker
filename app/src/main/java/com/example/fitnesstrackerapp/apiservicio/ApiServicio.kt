package com.example.fitnesstrackerapp.apiservicio

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.fitnesstrackerapp.objetos.login.UsuarioVerificar
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

interface ApiServicio {
    @POST("Login")
    suspend fun hacerLogin(@Body user:UsuarioVerificar):Boolean
}

@RequiresApi(Build.VERSION_CODES.O)
fun getRetrofitClient():Retrofit{
    val gson = GsonBuilder().registerTypeAdapter(
        LocalDate::class.java,
        JsonDeserializer { json, type, jsonDeserializationContext ->
            LocalDateTime.parse(json.asJsonPrimitive.asString,
                DateTimeFormatter.ofPattern("yyyy-MM-dd")) })
        .create()

    return Retrofit.Builder().baseUrl("http://192.168.1.132:8080/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(OkHttpClient())
        .build()
}