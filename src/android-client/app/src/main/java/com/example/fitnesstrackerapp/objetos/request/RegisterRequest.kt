package com.example.fitnesstrackerapp.objetos.request

import com.example.fitnesstrackerapp.objetos.response.Sexo
import com.google.gson.annotations.SerializedName

/**
 * Clase de datos que representa la solicitud de registro de un usuario.
 * Contiene información como correo electrónico, nombre de usuario, contraseña,
 * nombre completo, apellidos, fecha de nacimiento, altura, peso y sexo.
 */
data class RegisterRequest(
    /**
     * Correo electrónico del usuario.
     */
    @SerializedName("email")
    var email: String = "",

    /**
     * Nombre de usuario.
     */
    @SerializedName("username")
    var username: String = "",

    /**
     * Contraseña del usuario.
     */
    @SerializedName("password")
    var password: String = "",

    /**
     * Nombre completo del usuario.
     */
    @SerializedName("name")
    val name: String = "",

    /**
     * Primer apellido del usuario.
     */
    @SerializedName("firstSurname")
    val firstSurname: String = "",

    /**
     * Segundo apellido del usuario.
     */
    @SerializedName("secondSurname")
    val secondSurname: String = "",

    /**
     * Fecha de nacimiento del usuario en formato de cadena (ej. "YYYY-MM-DD").
     */
    @SerializedName("birthday")
    var birthday: String = "",

    /**
     * Altura del usuario en centímetros.
     */
    @SerializedName("height")
    val height: Int = 0,

    /**
     * Peso del usuario en kilogramos.
     */
    @SerializedName("weight")
    val weight: Int = 0,

    /**
     * Sexo del usuario.
     */
    @SerializedName("sex")
    val sex: Sexo = Sexo.Hombre
)