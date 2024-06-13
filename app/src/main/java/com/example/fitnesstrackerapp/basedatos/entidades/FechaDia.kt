package com.example.fitnesstrackerapp.basedatos.entidades

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate


/**
 * Entidad que representa una la fecha de la rutina diaria que está viendo el usuario.
 * La tabla correspondiente en la base de datos se llama "fecha".
 */
@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = "fecha")
data class FechaDia(
    /**
     * Identificador único de la fecha. Por defecto, su valor es 1, solo habrá un valor.
     */
    @PrimaryKey
    var id: Int = 1,

    /**
     * Fecha representada como una cadena de texto. Por defecto, se establece en la fecha actual.
     */
    var fecha: String = LocalDate.now().toString(),

    /**
     * Indicador de existencia de la fecha. Por defecto, su valor es false. Indica si hay una rutina en esa fecha insertada.
     */
    var existe: Boolean = false
)