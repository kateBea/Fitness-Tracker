package com.example.fitnesstrackerapp.basedatos.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fitnesstrackerapp.basedatos.entidades.FechaDia

@Dao
interface FechaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertar(fecha:FechaDia)

    @Update
    fun actualizar(fecha:FechaDia)

    @Query("SELECT fecha FROM fecha")
    fun getFecha():String

    @Query("Delete from fecha")
    fun borrar()

    @Query("SELECT existe FROM fecha")
    fun getInsertar():Boolean

}