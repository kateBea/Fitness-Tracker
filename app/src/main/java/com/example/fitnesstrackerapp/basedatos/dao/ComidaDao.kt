package com.example.fitnesstrackerapp.basedatos.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnesstrackerapp.basedatos.entidades.Comida

@Dao
interface ComidaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertar(comida: Comida)

    @Query("Select * from Comidas where Date(horaConsumo) = Date(:fecha)")
    fun getComidasDia(fecha:String):List<Comida>

    @Query("Select * from Comidas where comidaId = :id and Date(horaConsumo) = Date(:fecha)")
    fun verificarComidas(id:String,fecha:String):Comida

}