package com.example.fitnesstrackerapp.objetos.login

import com.google.gson.annotations.SerializedName

data class UsuarioVerificar(
    @SerializedName("email")
    var email:String = "",
    @SerializedName("password")
    var contrasena:String = ""
)