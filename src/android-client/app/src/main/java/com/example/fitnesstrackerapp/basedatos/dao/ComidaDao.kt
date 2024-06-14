package com.example.fitnesstrackerapp.basedatos.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnesstrackerapp.basedatos.entidades.Comida

/**
 * Data Access Object (DAO) para la entidad Comida en la base de datos.
 */
@Dao
interface ComidaDao {

    /**
     * Inserta una nueva comida en la base de datos.
     *
     * @param comida La comida a insertar.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertar(comida: Comida)

    /**
     * Obtiene todas las comidas para una fecha específica.
     *
     * @param fecha La fecha en formato de cadena (yyyy-MM-dd) para la cual se desean obtener las comidas.
     * @return Una lista de comidas para la fecha especificada.
     */
    @Query("SELECT * FROM Comidas WHERE Date(horaConsumo) = Date(:fecha)")
    fun getComidasDia(fecha: String): List<Comida>

    /**
     * Verifica si una comida específica existe para una fecha dada.
     *
     * @param id El ID de la comida a verificar.
     * @param fecha La fecha en formato de cadena (yyyy-MM-dd) para la cual se desea verificar la existencia de la comida.
     * @return La comida correspondiente al ID y fecha especificados, si existe; de lo contrario, null.
     */
    @Query("SELECT * FROM Comidas WHERE comidaId = :id AND Date(horaConsumo) = Date(:fecha)")
    fun verificarComidas(id: String, fecha: String): Comida
}