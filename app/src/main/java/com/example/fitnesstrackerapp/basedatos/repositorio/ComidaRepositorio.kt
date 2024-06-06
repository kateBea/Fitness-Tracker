package com.example.fitnesstrackerapp.basedatos.repositorio

import com.example.fitnesstrackerapp.basedatos.dao.ComidaDao
import com.example.fitnesstrackerapp.basedatos.entidades.Comida
import javax.inject.Inject

class ComidaRepositorio @Inject constructor(private val comidaDao: ComidaDao) {

    fun insertar(comida: Comida) = comidaDao.insertar(comida)
    fun getComidasDia(fecha: String) = comidaDao.getComidasDia(fecha)
    fun verificarComida(id:String,fecha:String) = comidaDao.verificarComidas(id,fecha)
}