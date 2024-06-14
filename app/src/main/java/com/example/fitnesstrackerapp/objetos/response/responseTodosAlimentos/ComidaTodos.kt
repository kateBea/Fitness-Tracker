package com.example.fitnesstrackerapp.objetos.response.responseTodosAlimentos

import com.google.gson.annotations.SerializedName

data class ComidaTodos(
    @SerializedName("id") val id: String = "0",
    @SerializedName("nombre") val nombre: String = "Producto Desconocido",
    @SerializedName("descripcion") val descripcion: String = "Sin descripción",
    @SerializedName("calorias") val calorias: Float = 0.0f,
    @SerializedName("proteinas") val proteinas: Float = 0.0f,
    @SerializedName("grasas") val grasas: Float = 0.0f,
    @SerializedName("carbohidratos") val carbohidratos: Float = 0.0f,
    @SerializedName("fechaRegistro") val fechaRegistro: String = "",
    @SerializedName("fechaUltimaModificacion") val fechaUltimaModificacion: String = ""
)