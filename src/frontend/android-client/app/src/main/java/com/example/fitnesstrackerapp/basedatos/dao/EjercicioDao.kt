package com.example.fitnesstrackerapp.basedatos.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnesstrackerapp.basedatos.entidades.Ejercicio

@Dao
interface EjercicioDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(ejercicio: Ejercicio)

    @Query("SELECT * FROM Ejercicio")
    fun getAll(): List<Ejercicio>

    @Query("SELECT * FROM Ejercicio where Date(fecha) = Date(:fecha)")
    fun getAllFecha(fecha:String): List<Ejercicio>
}