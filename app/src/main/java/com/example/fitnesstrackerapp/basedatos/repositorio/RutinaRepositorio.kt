package com.example.fitnesstrackerapp.basedatos.repositorio

import com.example.fitnesstrackerapp.basedatos.dao.ComidaDao
import com.example.fitnesstrackerapp.basedatos.dao.RutinaDao
import com.example.fitnesstrackerapp.basedatos.entidades.Rutina
import javax.inject.Inject

class RutinaRepositorio @Inject constructor(private val rutinaDao: RutinaDao){
    fun insertar(rutina: Rutina) = rutinaDao.insertar(rutina)
    fun getRutina(fecha:String) = rutinaDao.getRutina(fecha)
}