package com.example.fitnesstrackerapp.objetos.response.responseAlimentos

import com.google.gson.annotations.SerializedName

/**
 * Clase de datos que representa la respuesta de información de una comida.
 * Contiene el identificador de la comida, el nombre conocido, la descripción y la información de los nutrientes.
 */
data class ComidaResponse (
    /**
     * Identificador único de la comida.
     */
    @SerializedName("foodId")
    var id: String = "",

    /**
     * Nombre conocido de la comida.
     */
    @SerializedName("knownAs")
    var nombre: String = "",

    /**
     * Descripción de la comida.
     */
    @SerializedName("label")
    var descripcion: String = "",

    /**
     * Información de los nutrientes de la comida.
     */
    @SerializedName("nutrients")
    var nutrientes: InformacionNutrientes = InformacionNutrientes()
)