package com.example.fitnesstrackerapp.objetos.response.responseAlimentos

import com.google.gson.annotations.SerializedName

data class InformacionComida(
    @SerializedName("food")
    var comida:ComidaResponse = ComidaResponse()
)