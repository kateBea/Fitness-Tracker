package com.example.fitnesstrackerapp.basedatos.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnesstrackerapp.basedatos.entidades.Rutina

@Dao
interface RutinaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertar(rutina: Rutina)

    @Query("SELECT * FROM Rutinas where Date(fechaSeguimiendo) = Date(:fecha)")
    fun getRutina(fecha:String):Rutina
}