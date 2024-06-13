package com.example.fitnesstrackerapp.basedatos.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fitnesstrackerapp.basedatos.entidades.FechaDia

/**
 * Interfaz de Objeto de Acceso a Datos (DAO) para realizar operaciones en la entidad FechaDia.
 */
@Dao
interface FechaDao {

    /**
     * Inserta una nueva entidad FechaDia en la base de datos.
     * Si ocurre un conflicto, la operación de inserción se ignora.
     *
     * @param fecha La entidad FechaDia a insertar.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertar(fecha: FechaDia)

    /**
     * Actualiza una entidad FechaDia existente en la base de datos.
     *
     * @param fecha La entidad FechaDia a actualizar.
     */
    @Update
    fun actualizar(fecha: FechaDia)

    /**
     * Recupera la fecha de la tabla fecha.
     *
     * @return Un String que representa la fecha.
     */
    @Query("SELECT fecha FROM fecha")
    fun getFecha(): String

    /**
     * Borra todas las entradas de la tabla fecha.
     */
    @Query("Delete from fecha")
    fun borrar()

    /**
     * Verifica si existe la rutina para esa fecha.
     *
     * @return Un booleano que indica si existe una entrada.
     */
    @Query("SELECT existe FROM fecha")
    fun getInsertar(): Boolean
}