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

/**
 * Clase de base de datos que define la estructura y la versión de la base de datos.
 *
 * @param entities Arreglo de clases de entidad que representan las tablas de la base de datos.
 * @param version Número de versión de la base de datos. Debe incrementarse si se modifican las entidades.
 * @param exportSchema Indica si el esquema de la base de datos debe ser exportado cuando se exporta el archivo APK.
 */
@Database(
    entities = [UsuarioInfo::class, Comida::class, Rutina::class, FechaDia::class],
    version = 16,
    exportSchema = false
)
abstract class BaseDatos : RoomDatabase() {
    /**
     * Método abstracto para obtener el DAO de usuarios.
     *
     * @return Objeto de acceso a datos (DAO) para la entidad de Usuario.
     */
    abstract fun userDao(): UsuarioDao

    /**
     * Método abstracto para obtener el DAO de fechas.
     *
     * @return Objeto de acceso a datos (DAO) para la entidad de Fecha.
     */
    abstract fun fechaDao(): FechaDao

    /**
     * Método abstracto para obtener el DAO de rutinas.
     *
     * @return Objeto de acceso a datos (DAO) para la entidad de Rutina.
     */
    abstract fun rutinaDao(): RutinaDao

    /**
     * Método abstracto para obtener el DAO de comidas.
     *
     * @return Objeto de acceso a datos (DAO) para la entidad de Comida.
     */
    abstract fun comidaDao(): ComidaDao
}