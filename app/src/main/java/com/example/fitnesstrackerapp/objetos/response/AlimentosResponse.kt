package com.example.fitnesstrackerapp.objetos.response

import com.google.gson.annotations.SerializedName

data class AlimentosResponse(
    @SerializedName("data")
    var rutinas:List<RutinasResponse> = listOf(),
    @SerializedName("success")
    var success:Boolean = true,
    @SerializedName("responseDescription")
    var responseDescription:String = "",
)