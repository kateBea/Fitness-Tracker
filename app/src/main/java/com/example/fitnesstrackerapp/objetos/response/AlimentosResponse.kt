package com.example.fitnesstrackerapp.objetos.response

import com.google.gson.annotations.SerializedName

/**
 * Clase de datos que representa la respuesta de alimentos.
 * Contiene una lista de objetos RutinasResponse, un indicador de éxito y una descripción de la respuesta.
 */
data class AlimentosResponse(
    /**
     * Lista de objetos RutinasResponse que representan los datos de alimentos.
     */
    @SerializedName("data")
    var rutinas: List<RutinasResponse> = listOf(),

    /**
     * Indicador de éxito de la respuesta.
     */
    @SerializedName("success")
    var success: Boolean = true,

    /**
     * Descripción de la respuesta.
     */
    @SerializedName("responseDescription")
    var responseDescription: String = ""
)