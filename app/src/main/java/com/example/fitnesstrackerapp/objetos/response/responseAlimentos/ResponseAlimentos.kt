package com.example.fitnesstrackerapp.objetos.response.responseAlimentos

import com.google.gson.annotations.SerializedName

/**
 * Clase de datos que encapsula la respuesta de alimentos.
 * Contiene un objeto Alimentos que representa los resultados de la consulta.
 */
data class ResponseAlimentos(
    /**
     * Objeto Alimentos que contiene las pistas o resultados de la consulta.
     */
    @SerializedName("result")
    var pistas: Alimentos = Alimentos()
)