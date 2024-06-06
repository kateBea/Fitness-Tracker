package com.example.fitnesstrackerapp.basedatos.repositorio

import com.example.fitnesstrackerapp.basedatos.dao.UsuarioDao
import com.example.fitnesstrackerapp.basedatos.entidades.UsuarioInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsuarioRepositorio  @Inject constructor(private val usuarioDao: UsuarioDao){
    fun insertarUsuario(usuarioInfo: UsuarioInfo) = usuarioDao.insertarusuario(usuarioInfo)
    fun getUsuario() = usuarioDao.getUser()
    fun actualizarUsuario(usuarioInfo: UsuarioInfo) = usuarioDao.actualizarusuario(usuarioInfo)
    fun borrarUsuario() = usuarioDao.borrarUsuarios()
    fun getNombreUsuario() = usuarioDao.getNombreUsuario()

    fun getToken() = usuarioDao.getToken()
    fun getEmail() = usuarioDao.getEmail()
}