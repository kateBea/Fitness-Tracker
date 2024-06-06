package com.example.fitnesstrackerapp.objetos.response.responseAlimentos

import com.google.gson.annotations.SerializedName

data class ResponseAlimentos(
    @SerializedName("result")
    var pistas:Alimentos = Alimentos()
)