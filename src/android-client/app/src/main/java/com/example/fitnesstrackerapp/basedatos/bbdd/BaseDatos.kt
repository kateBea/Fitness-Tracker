package com.example.fitnesstrackerapp.basedatos.bbdd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fitnesstrackerapp.basedatos.dao.UsuarioDao
import com.example.fitnesstrackerapp.basedatos.entidades.UsuarioInfo

@Database(
    entities = [UsuarioInfo::class],
    version = 6,
    exportSchema = false
)

abstract class BaseDatos :RoomDatabase() {
    abstract fun userDao():UsuarioDao
}