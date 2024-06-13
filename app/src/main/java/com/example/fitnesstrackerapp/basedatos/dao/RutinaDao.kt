package com.example.fitnesstrackerapp.basedatos.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnesstrackerapp.basedatos.entidades.Rutina

/**
 * Interfaz de Objeto de Acceso a Datos (DAO) para realizar operaciones en la entidad Rutina.
 */
@Dao
interface RutinaDao {

    /**
     * Inserta una nueva entidad Rutina en la base de datos.
     * Si ocurre un conflicto, la operación de inserción se ignora.
     *
     * @param rutina La entidad Rutina a insertar.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertar(rutina: Rutina)

    /**
     * Recupera una Rutina de la tabla Rutinas para una fecha específica.
     *
     * @param fecha La fecha para la cual se desea recuperar la rutina.
     * @return La entidad Rutina correspondiente a la fecha dada.
     */
    @Query("SELECT * FROM Rutinas where Date(fechaSeguimiendo) = Date(:fecha)")
    fun getRutina(fecha: String): Rutina

    /**
     * Actualiza la cantidad de agua consumida en una Rutina para una fecha específica.
     *
     * @param aguaconsumida La cantidad de agua consumida a actualizar.
     * @param fecha La fecha de la rutina cuya cantidad de agua consumida se desea actualizar.
     */
    @Query("UPDATE Rutinas set aguaconsumida = :aguaconsumida where Date(fechaSeguimiendo) = Date(:fecha)")
    fun actualizarAgua(aguaconsumida: Int, fecha: String)

    /**
     * Recupera la cantidad de agua consumida de una Rutina para una fecha específica.
     *
     * @param fecha La fecha para la cual se desea recuperar la cantidad de agua consumida.
     * @return Un entero que representa la cantidad de agua consumida.
     */
    @Query("Select aguaconsumida from Rutinas where Date(fechaSeguimiendo) = Date(:fecha)")
    fun cogerAgua(fecha: String): Int
}