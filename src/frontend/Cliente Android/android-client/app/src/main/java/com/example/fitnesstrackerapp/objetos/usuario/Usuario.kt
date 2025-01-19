package com.example.fitnesstrackerapp.objetos.usuario

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.fitnesstrackerapp.objetos.response.Sexo
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Clase que representa un usuario con sus atributos básicos.
 */
@RequiresApi(Build.VERSION_CODES.O)
data class Usuario(
    /**
     * Nombre de usuario.
     */
    @SerializedName("nombreUsuario")
    val nombreUsuario: String = "",

    /**
     * Nombre del usuario.
     */
    @SerializedName("nombre")
    val nombre: String = "",

    /**
     * Primer apellido del usuario.
     */
    @SerializedName("primerApellido")
    val apellido: String = "",

    /**
     * Segundo apellido del usuario.
     */
    @SerializedName("segundoApellido")
    val segundoApellido: String = "",

    /**
     * Fecha de nacimiento del usuario en formato String.
     */
    @SerializedName("fechaDeNacimiento")
    val fechaNacimiento: String = "",

    /**
     * Fecha de registro del usuario en formato String.
     */
    @SerializedName("fechaRegistro")
    val fechaRegistro: String = "",

    /**
     * Altura del usuario en metros.
     */
    @SerializedName("altura")
    val altura: Float = 0f,

    /**
     * Peso del usuario en kilogramos.
     */
    @SerializedName("peso")
    val peso: Float = 0f,

    /**
     * Sexo del usuario.
     * Puede ser Sexo.Hombre o Sexo.Mujer.
     */
    @SerializedName("sexo")
    val sexo: Sexo = Sexo.Hombre
)