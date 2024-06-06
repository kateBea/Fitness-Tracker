package com.example.fitnesstrackerapp.basedatos.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Index
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fitnesstrackerapp.basedatos.entidades.UsuarioInfo

@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertarusuario(UsuarioInfo: UsuarioInfo)

    @Query("Select * from USUARIO")
    fun getUser():UsuarioInfo

    @Update
    fun actualizarusuario(UsuarioInfo: UsuarioInfo)

    @Query("Delete from USUARIO")
    fun borrarUsuarios()

    @Query("Select nombreUsuario from USUARIO")
    fun getNombreUsuario():String?

    @Query("Select token from USUARIO")
    fun getToken():String?

    @Query("Select email from USUARIO")
    fun getEmail():String?
}