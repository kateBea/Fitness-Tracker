package com.example.fitnesstrackerapp.basedatos.repositorio

import com.example.fitnesstrackerapp.basedatos.dao.FechaDao
import com.example.fitnesstrackerapp.basedatos.dao.UsuarioDao
import com.example.fitnesstrackerapp.basedatos.entidades.FechaDia
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FechaRepositorio @Inject constructor(private val fechaDao: FechaDao) {
    fun insertar(fecha:FechaDia) = fechaDao.insertar(fecha)
    fun getFecha() = fechaDao.getFecha()
    fun actualizar(fecha:FechaDia) = fechaDao.actualizar(fecha)
    fun borrar() = fechaDao.borrar()
    fun existeRutina() = fechaDao.getInsertar()
}