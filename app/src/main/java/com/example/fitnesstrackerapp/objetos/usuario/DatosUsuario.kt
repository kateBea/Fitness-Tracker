package com.example.fitnesstrackerapp.objetos.usuario

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName

/**
 * Clase que representa los datos de un usuario obtenidos de al API.
 */
@RequiresApi(Build.VERSION_CODES.O)
data class DatosUsuario(
    /**
     * Objeto que contiene la información del usuario.
     */
    @SerializedName("data")
    val usuario: Usuario = Usuario(),

    /**
     * Descripción de la respuesta obtenida.
     */
    @SerializedName("responseDescription")
    val responseDescription: String = "",

    /**
     * Indicador de éxito de la respuesta.
     */
    @SerializedName("success")
    val success: Boolean = false
)