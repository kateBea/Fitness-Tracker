package com.example.fitnesstrackerapp.basedatos.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fitnesstrackerapp.objetos.response.Sexo

@Entity ( tableName = "USUARIO")
data class UsuarioInfo(
    @PrimaryKey
    var email:String = "",
    var nombreUsuario:String = "",
    var contrasena:String = "",
    var nombre:String = "",
    var apellido:String = "",
    var segundoApellido:String = "",
    var fechaNacimiento:String = "",
    var fechaRegistro:String = "",
    var token:String = "",
    var fechaUltimaModificacion:String = "",
    var altura:Float = 0f,
    var peso:Float = 0f,
    var sexo: Sexo = Sexo.Hombre
)