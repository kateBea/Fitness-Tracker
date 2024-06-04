package com.example.fitnesstrackerapp.objetos.usuario

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.fitnesstrackerapp.objetos.response.Sexo
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
data class Usuario(
    @SerializedName("correo_electronico")
    val email: String = "",

    @SerializedName("nombre_usuario")
    val nombreUsuario: String = "",

    @SerializedName("nombre")
    val nombre: String = "",

    @SerializedName("primer_apellido")
    val apellido: String = "",

    @SerializedName("segundo_apellido")
    val segundoApellido: String = "",

    @SerializedName("fecha_nacimiento")
    val fechaNacimiento: String = "",

    @SerializedName("fecha_alta")
    val fechaRegistro: String = "",

    @SerializedName("altura")
    val altura: Float = 0f,

    @SerializedName("peso")
    val peso: Float = 0f,

    @SerializedName("sexo")
    val sexo: Sexo = Sexo.Hombre
)