package com.example.fitnesstrackerapp.objetos.response.responseAlimentos

import com.google.gson.annotations.SerializedName

/**
 * Clase de datos que encapsula la información detallada de una comida.
 * Contiene un objeto ComidaResponse que proporciona detalles específicos de la comida.
 */
data class InformacionComida(
    /**
     * Objeto ComidaResponse que contiene la información detallada de la comida.
     */
    @SerializedName("food")
    var comida: ComidaResponse = ComidaResponse()
)