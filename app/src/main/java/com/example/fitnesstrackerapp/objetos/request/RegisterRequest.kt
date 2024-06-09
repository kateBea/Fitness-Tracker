package com.example.fitnesstrackerapp.objetos.request

import com.example.fitnesstrackerapp.objetos.response.Sexo
import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("email")
    var email: String = "",
    @SerializedName("username")
    var username: String = "",
    @SerializedName("password")
    var password: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("firstSurname")
    val firstSurname: String = "",
    @SerializedName("secondSurname")
    val secondSurname: String = "",
    @SerializedName("birthday")
    val birthday: String = "",
    @SerializedName("height")
    val height: Int = 0,
    @SerializedName("weight")
    val weight: Int = 0,
    @SerializedName("sex")
    val sex: Sexo = Sexo.Hombre
)