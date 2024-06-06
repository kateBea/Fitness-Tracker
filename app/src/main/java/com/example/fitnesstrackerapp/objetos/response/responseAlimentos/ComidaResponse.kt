package com.example.fitnesstrackerapp.objetos.response.responseAlimentos

import com.google.gson.annotations.SerializedName

data class ComidaResponse (
    @SerializedName("foodId")
    var id:String = "",
    @SerializedName("knownAs")
    var nombre:String = "",
    @SerializedName("label")
    var descripcion:String = "",
    @SerializedName("nutrients")
    var nutrientes:InformacionNutrientes = InformacionNutrientes()
)