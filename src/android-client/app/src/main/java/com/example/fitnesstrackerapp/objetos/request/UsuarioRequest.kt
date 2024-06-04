package com.example.fitnesstrackerapp.objetos.request

import androidx.room.PrimaryKey
import com.example.fitnesstrackerapp.objetos.response.Sexo
import com.google.gson.annotations.SerializedName

data class UsuarioRequest(
    @SerializedName("correo_electronico")
    var email:String = "",
    @SerializedName("nombre_usuario")
    var nombreUsuario:String = "",
    @SerializedName("nombre")
    var nombre:String = "",
    @SerializedName("primer_apellido")
    var apellido:String = "",
    @SerializedName("segundo_apellido")
    var segundoApellido:String = "",
    @SerializedName("fecha_nacimiento")
    var fechaNacimiento:String = "",
    @SerializedName("altura")
    var altura:Float = 0f,
    @SerializedName("peso")
    var peso:Float = 0f,
    @SerializedName("sexo")
    var sexo: Sexo = Sexo.Hombre
)