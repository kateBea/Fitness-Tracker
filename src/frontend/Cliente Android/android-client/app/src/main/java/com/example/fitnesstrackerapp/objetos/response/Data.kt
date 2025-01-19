package com.example.fitnesstrackerapp.objetos.response

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

/**
 * Clase de datos que representa la información principal de sesión de usuario.
 * Contiene nombre, nombre de usuario, apellidos, token de sesión, fecha de inicio de sesión,
 * fecha de expiración del token y duración del token.
 */
@RequiresApi(api = Build.VERSION_CODES.O)
data class Data(
    /**
     * Nombre del usuario.
     */
    @SerializedName("name")
    val nombre: String? = "",

    /**
     * Nombre de usuario.
     */
    @SerializedName("username")
    val username: String? = "",

    /**
     * Primer apellido del usuario.
     */
    @SerializedName("firstSurname")
    val apellido: String? = "",

    /**
     * Segundo apellido del usuario.
     */
    @SerializedName("secondSurname")
    val segundoApellido: String? = "",

    /**
     * Token de sesión del usuario.
     */
    @SerializedName("token")
    val token: String? = "",

    /**
     * Fecha de inicio de sesión del usuario en formato de cadena.
     */
    @SerializedName("loginDate")
    val loginDate: String? = "",

    /**
     * Fecha de expiración del token de sesión en formato de cadena.
     */
    @SerializedName("tokenExpirationDate")
    val tokenExpirationDate: String? = "",

    /**
     * Duración del token de sesión en minutos.
     */
    @SerializedName("tokenDuration")
    val tokenDuration: Int? = 0
)