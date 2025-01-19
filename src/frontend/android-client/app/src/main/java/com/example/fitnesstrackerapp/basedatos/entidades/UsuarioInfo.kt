package com.example.fitnesstrackerapp.basedatos.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fitnesstrackerapp.objetos.response.Sexo

/**
 * Clase que representa la entidad de información de usuario en la base de datos.
 *
 * @property email Correo electrónico del usuario (clave primaria).
 * @property nombreUsuario Nombre de usuario del usuario.
 * @property contrasena Contraseña del usuario.
 * @property nombre Nombre del usuario.
 * @property apellido Apellido del usuario.
 * @property segundoApellido Segundo apellido del usuario.
 * @property fechaNacimiento Fecha de nacimiento del usuario (en formato de cadena).
 * @property fechaRegistro Fecha de registro del usuario (en formato de cadena).
 * @property token Token de autenticación del usuario.
 * @property fechaUltimaModificacion Fecha de última modificación del usuario (en formato de cadena).
 * @property altura Altura del usuario en metros.
 * @property peso Peso del usuario en kilogramos.
 * @property sexo Sexo del usuario (Hombre o Mujer).
 */
@Entity(tableName = "USUARIO")
data class UsuarioInfo(
    @PrimaryKey
    var email: String = "",
    var nombreUsuario: String = "",
    var contrasena: String = "",
    var nombre: String = "",
    var apellido: String = "",
    var segundoApellido: String = "",
    var fechaNacimiento: String = "",
    var fechaRegistro: String = "",
    var token: String = "",
    var fechaUltimaModificacion: String = "",
    var altura: Float = 0f,
    var peso: Float = 0f,
    var sexo: Sexo = Sexo.Hombre
)