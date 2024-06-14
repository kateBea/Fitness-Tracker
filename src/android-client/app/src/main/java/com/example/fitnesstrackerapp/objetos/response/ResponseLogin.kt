package com.example.fitnesstrackerapp.objetos.response

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

/**
 * Clase de datos que representa la respuesta de inicio de sesión.
 * Contiene datos de sesión del usuario, descripción de la respuesta y estado de éxito.
 */
@RequiresApi(Build.VERSION_CODES.O)
data class ResponseLogin(
    /**
     * Datos de sesión del usuario.
     */
    @SerializedName("data")
    var data: Data = Data(),

    /**
     * Descripción de la respuesta.
     */
    @SerializedName("response_description")
    var responseDescription: String = "",

    /**
     * Estado de éxito de la respuesta.
     */
    @SerializedName("success")
    var success: Boolean = false
)

/**
 * Enumeración que define los posibles valores de sexo.
 */
enum class Sexo {
    /**
     * Representa el sexo masculino.
     */
    Hombre,

    /**
     * Representa el sexo femenino.
     */
    Mujer,

    /**
     * Representa otro género o sexo.
     */
    Otro
}