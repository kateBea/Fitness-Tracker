package com.example.fitnesstrackerapp.basedatos.repositorio

import com.example.fitnesstrackerapp.basedatos.dao.ComidaDao
import com.example.fitnesstrackerapp.basedatos.dao.EjercicioDao
import com.example.fitnesstrackerapp.basedatos.entidades.Ejercicio
import javax.inject.Inject

class EjercicioRepositorio @Inject constructor(private val ejercicioDao: EjercicioDao)  {
    fun getAll() = ejercicioDao.getAll()
    fun insert(ejercicio: Ejercicio) = ejercicioDao.insert(ejercicio)
    fun ejerciciosFecha(fecha:String) = ejercicioDao.getAllFecha(fecha)
}