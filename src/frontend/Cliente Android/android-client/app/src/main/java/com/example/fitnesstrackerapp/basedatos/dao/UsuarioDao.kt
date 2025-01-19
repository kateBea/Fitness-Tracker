package com.example.fitnesstrackerapp.basedatos.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Index
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fitnesstrackerapp.basedatos.entidades.UsuarioInfo

/**
 * Interfaz de Objeto de Acceso a Datos (DAO) para realizar operaciones en la entidad UsuarioInfo.
 */
@Dao
interface UsuarioDao {

    /**
     * Inserta una nueva entidad UsuarioInfo en la base de datos.
     * Si ocurre un conflicto, la operación de inserción se ignora.
     *
     * @param UsuarioInfo La entidad UsuarioInfo a insertar.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertarusuario(UsuarioInfo: UsuarioInfo)

    /**
     * Recupera la información del usuario de la tabla USUARIO.
     *
     * @return La entidad UsuarioInfo con la información del usuario.
     */
    @Query("Select * from USUARIO")
    fun getUser(): UsuarioInfo

    /**
     * Actualiza una entidad UsuarioInfo existente en la base de datos.
     *
     * @param UsuarioInfo La entidad UsuarioInfo a actualizar.
     */
    @Update
    fun actualizarusuario(UsuarioInfo: UsuarioInfo)

    /**
     * Borra todas las entradas de la tabla USUARIO.
     */
    @Query("Delete from USUARIO")
    fun borrarUsuarios()

    /**
     * Recupera el nombre de usuario de la tabla USUARIO.
     *
     * @return Un String que representa el nombre de usuario, o null si no existe.
     */
    @Query("Select nombreUsuario from USUARIO")
    fun getNombreUsuario(): String?

    /**
     * Recupera el token del usuario de la tabla USUARIO.
     *
     * @return Un String que representa el token, o null si no existe.
     */
    @Query("Select token from USUARIO")
    fun getToken(): String?

    /**
     * Recupera el email del usuario de la tabla USUARIO.
     *
     * @return Un String que representa el email, o null si no existe.
     */
    @Query("Select email from USUARIO")
    fun getEmail(): String?
}