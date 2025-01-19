package com.example.fitnesstrackerapp.basedatos.repositorio

import com.example.fitnesstrackerapp.basedatos.dao.UsuarioDao
import com.example.fitnesstrackerapp.basedatos.entidades.UsuarioInfo
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repositorio para manejar operaciones de la entidad UsuarioInfo utilizando UsuarioDao.
 *
 * @param usuarioDao El objeto UsuarioDao utilizado para acceder a la base de datos.
 */
class UsuarioRepositorio @Inject constructor(private val usuarioDao: UsuarioDao) {

    /**
     * Inserta una nueva entidad UsuarioInfo en la base de datos.
     *
     * @param usuarioInfo La entidad UsuarioInfo a insertar.
     */
    fun insertarUsuario(usuarioInfo: UsuarioInfo) = usuarioDao.insertarusuario(usuarioInfo)

    /**
     * Recupera la información del usuario desde la tabla USUARIO.
     *
     * @return La entidad UsuarioInfo con la información del usuario.
     */
    fun getUsuario() = usuarioDao.getUser()

    /**
     * Actualiza una entidad UsuarioInfo existente en la base de datos.
     *
     * @param usuarioInfo La entidad UsuarioInfo a actualizar.
     */
    fun actualizarUsuario(usuarioInfo: UsuarioInfo) = usuarioDao.actualizarusuario(usuarioInfo)

    /**
     * Borra todas las entradas de la tabla USUARIO.
     */
    fun borrarUsuario() = usuarioDao.borrarUsuarios()

    /**
     * Recupera el nombre de usuario desde la tabla USUARIO.
     *
     * @return Un String que representa el nombre de usuario, o null si no existe.
     */
    fun getNombreUsuario() = usuarioDao.getNombreUsuario()

    /**
     * Recupera el token del usuario desde la tabla USUARIO.
     *
     * @return Un String que representa el token, o null si no existe.
     */
    fun getToken() = usuarioDao.getToken()

    /**
     * Recupera el email del usuario desde la tabla USUARIO.
     *
     * @return Un String que representa el email, o null si no existe.
     */
    fun getEmail() = usuarioDao.getEmail()
}