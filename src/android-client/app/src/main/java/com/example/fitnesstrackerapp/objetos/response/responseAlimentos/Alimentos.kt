package com.example.fitnesstrackerapp.objetos.response.responseAlimentos

import com.google.gson.annotations.SerializedName

/**
 * Clase de datos que representa una lista de alimentos con información asociada.
 * Contiene una lista de objetos InformacionComida y un texto descriptivo.
 */
data class Alimentos(
    /**
     * Lista de objetos InformacionComida que proporcionan detalles sobre los alimentos.
     */
    @SerializedName("hints")
    var alimentos: List<InformacionComida> = listOf(),

    /**
     * Texto descriptivo asociado a los alimentos.
     */
    @SerializedName("text")
    var texto: String = ""
)