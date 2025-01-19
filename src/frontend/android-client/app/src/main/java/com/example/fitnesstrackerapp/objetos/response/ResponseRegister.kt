package com.example.fitnesstrackerapp.objetos.response

import com.google.gson.annotations.SerializedName


/**
 * Clase de datos que representa la respuesta de registro.
 * Indica si el registro fue exitoso o no.
 */
data class ResponseRegister(
    /**
     * Indicador de éxito del registro.
     */
    @SerializedName("success")
    var valido: Boolean = false
)