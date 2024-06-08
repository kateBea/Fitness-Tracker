package com.example.fitnesstrackerapp.basedatos.bbdd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fitnesstrackerapp.basedatos.dao.ComidaDao
import com.example.fitnesstrackerapp.basedatos.dao.FechaDao
import com.example.fitnesstrackerapp.basedatos.dao.RutinaDao
import com.example.fitnesstrackerapp.basedatos.dao.UsuarioDao
import com.example.fitnesstrackerapp.basedatos.entidades.Comida
import com.example.fitnesstrackerapp.basedatos.entidades.FechaDia
import com.example.fitnesstrackerapp.basedatos.entidades.Rutina
import com.example.fitnesstrackerapp.basedatos.entidades.UsuarioInfo

@Database(
    entities = [UsuarioInfo::class,Comida::class, Rutina::class,FechaDia::class],
    version = 16,
    exportSchema = false
)

abstract class BaseDatos :RoomDatabase() {
    abstract fun userDao():UsuarioDao
    abstract fun fechaDao():FechaDao
    abstract fun rutinaDao(): RutinaDao
    abstract fun comidaDao():ComidaDao
}