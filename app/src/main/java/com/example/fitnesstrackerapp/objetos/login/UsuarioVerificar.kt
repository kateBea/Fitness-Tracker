package com.example.fitnesstrackerapp.objetos.login

import com.google.gson.annotations.SerializedName

/**
 * Clase de datos que representa las credenciales de un usuario para verificación.
 * Contiene el correo electrónico y la contraseña del usuario.
 */
data class UsuarioVerificar(
    /**
     * Correo electrónico del usuario.
     */
    @SerializedName("email")
    var email: String = "",

    /**
     * Contraseña del usuario.
     */
    @SerializedName("password")
    var contrasena: String = ""
)