package com.example.fitnesstrackerapp.objetos.response.responseTodosAlimentos

import com.google.gson.annotations.SerializedName

data class ResponseAlimentosTodos(
    @SerializedName("data")
    val comidas:List<ComidaTodos>
)