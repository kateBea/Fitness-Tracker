package com.example.fitnesstrackerapp.objetos.response.responseAlimentos

import com.google.gson.annotations.SerializedName

data class Alimentos(
    @SerializedName("hints")
    var alimentos:List<InformacionComida> = listOf(),
    @SerializedName("text")
    var texto:String = ""
)