package com.example.fitnesstrackerapp.objetos.request

import androidx.room.PrimaryKey
import com.example.fitnesstrackerapp.objetos.response.Sexo
import com.google.gson.annotations.SerializedName

data class UsuarioRequest(
    @SerializedName("nombreUsuario")
    var nombreUsuario:String = "",
    @SerializedName("nombre")
    var nombre:String = "",
    @SerializedName("primerApellido")
    var apellido:String = "",
    @SerializedName("segundoApellido")
    var segundoApellido:String = "",
    @SerializedName("fechaDeNacimiento")
    var fechaNacimiento:String = "",
    @SerializedName("altura")
    var altura:Float = 0f,
    @SerializedName("peso")
    var peso:Float = 0f,
    @SerializedName("sexo")
    var sexo: Sexo = Sexo.Hombre
)