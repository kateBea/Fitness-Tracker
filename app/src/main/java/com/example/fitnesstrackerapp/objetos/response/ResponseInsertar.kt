package com.example.fitnesstrackerapp.objetos.response

import com.google.gson.annotations.SerializedName

data class ResponseInsertar(
    @SerializedName("id")
    var id:String = ""
)