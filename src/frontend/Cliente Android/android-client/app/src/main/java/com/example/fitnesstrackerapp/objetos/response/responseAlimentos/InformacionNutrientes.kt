package com.example.fitnesstrackerapp.objetos.response.responseAlimentos

import com.google.gson.annotations.SerializedName

/**
 * Clase de datos que representa la información de nutrientes de un alimento.
 * Contiene valores para calorías, proteínas, grasas y carbohidratos.
 */
data class InformacionNutrientes (
    /**
     * Valor de calorías del alimento.
     */
    @SerializedName("calories")
    var calorias: Float = 0f,

    /**
     * Valor de proteínas del alimento.
     */
    @SerializedName("protein")
    var proteinas: Float = 0f,

    /**
     * Valor de grasas del alimento.
     */
    @SerializedName("fat")
    var grasas: Float = 0f,

    /**
     * Valor de carbohidratos del alimento.
     */
    @SerializedName("carbohydrates")
    var carbohidratos: Float = 0f
)