package com.example.fitnesstrackerapp.objetos.response.responseAlimentos

import com.google.gson.annotations.SerializedName

data class InformacionNutrientes (
    @SerializedName("calories")
    var calorias:Float = 0f,
    @SerializedName("protein")
    var proteinas:Float = 0f,
    @SerializedName("fat")
    var grasas:Float = 0f,
    @SerializedName("carbohydrates")
    var carbohidratos:Float = 0f,
)