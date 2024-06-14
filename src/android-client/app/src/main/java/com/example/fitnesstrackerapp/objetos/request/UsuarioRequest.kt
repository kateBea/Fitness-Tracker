package com.example.fitnesstrackerapp.objetos.request

import androidx.room.PrimaryKey
import com.example.fitnesstrackerapp.objetos.response.Sexo
import com.google.gson.annotations.SerializedName

/**
 * Clase de datos que representa una solicitud de información de usuario.
 * Contiene datos como nombre de usuario, nombre completo, apellidos,
 * fecha de nacimiento, altura, peso y sexo.
 */
data class UsuarioRequest(
    /**
     * Nombre de usuario.
     */
    @SerializedName("nombreUsuario")
    var nombreUsuario: String = "",

    /**
     * Nombre del usuario.
     */
    @SerializedName("nombre")
    var nombre: String = "",

    /**
     * Primer apellido del usuario.
     */
    @SerializedName("primerApellido")
    var apellido: String = "",

    /**
     * Segundo apellido del usuario.
     */
    @SerializedName("segundoApellido")
    var segundoApellido: String = "",

    /**
     * Fecha de nacimiento del usuario en formato de cadena (ej. "YYYY-MM-DD").
     */
    @SerializedName("fechaDeNacimiento")
    var fechaNacimiento: String = "",

    /**
     * Altura del usuario en metros.
     */
    @SerializedName("altura")
    var altura: Float = 0f,

    /**
     * Peso del usuario en kilogramos.
     */
    @SerializedName("peso")
    var peso: Float = 0f,

    /**
     * Sexo del usuario.
     */
    @SerializedName("sexo")
    var sexo: Sexo = Sexo.Hombre
)