package com.example.fitnesstrackerapp.basedatos.repositorio

import com.example.fitnesstrackerapp.basedatos.dao.FechaDao
import com.example.fitnesstrackerapp.basedatos.dao.UsuarioDao
import com.example.fitnesstrackerapp.basedatos.entidades.FechaDia
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repositorio para manejar operaciones de la entidad FechaDia utilizando FechaDao.
 *
 * @param fechaDao El objeto FechaDao utilizado para acceder a la base de datos.
 */
class FechaRepositorio @Inject constructor(private val fechaDao: FechaDao) {

    /**
     * Inserta una nueva entidad FechaDia en la base de datos.
     *
     * @param fecha La entidad FechaDia a insertar.
     */
    fun insertar(fecha: FechaDia) = fechaDao.insertar(fecha)

    /**
     * Recupera la fecha de la tabla fecha.
     *
     * @return Un String que representa la fecha.
     */
    fun getFecha() = fechaDao.getFecha()

    /**
     * Actualiza una entidad FechaDia existente en la base de datos.
     *
     * @param fecha La entidad FechaDia a actualizar.
     */
    fun actualizar(fecha: FechaDia) = fechaDao.actualizar(fecha)

    /**
     * Borra todas las entradas de la tabla fecha.
     */
    fun borrar() = fechaDao.borrar()

    /**
     * Verifica si existe una entrada en la tabla fecha.
     *
     * @return Un booleano que indica si existe una entrada.
     */
    fun existeRutina() = fechaDao.getInsertar()
}