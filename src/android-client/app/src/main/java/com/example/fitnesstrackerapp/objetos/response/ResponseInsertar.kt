package com.example.fitnesstrackerapp.objetos.response

import com.google.gson.annotations.SerializedName

/**
 * Clase de datos que representa la respuesta de inserción.
 * Contiene el identificador generado para el objeto insertado.
 */
data class ResponseInsertar(
    /**
     * Identificador generado para el objeto insertado.
     */
    @SerializedName("id")
    var id: String = ""
)