package com.example.fitnesstrackerapp.objetos.usuario

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.fitnesstrackerapp.objetos.response.Sexo
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
data class Usuario(

    @SerializedName("nombreUsuario")
    val nombreUsuario: String = "",

    @SerializedName("nombre")
    val nombre: String = "",

    @SerializedName("primerApellido")
    val apellido: String = "",

    @SerializedName("segundoApellido")
    val segundoApellido: String = "",

    @SerializedName("fechaDeNacimiento")
    val fechaNacimiento: String = "",

    @SerializedName("fechaRegistro")
    val fechaRegistro: String = "",

    @SerializedName("altura")
    val altura: Float = 0f,

    @SerializedName("peso")
    val peso: Float = 0f,

    @SerializedName("sexo")
    val sexo: Sexo = Sexo.Hombre
)