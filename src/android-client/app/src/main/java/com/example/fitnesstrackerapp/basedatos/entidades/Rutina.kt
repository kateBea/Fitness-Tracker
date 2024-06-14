package com.example.fitnesstrackerapp.basedatos.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Entidad que representa una rutina en la base de datos.
 * La tabla correspondiente en la base de datos se llama "Rutinas".
 */
@Entity(tableName = "Rutinas")
data class Rutina (
    /**
     * Identificador único de la rutina. Se genera automáticamente.
     */
    @PrimaryKey(autoGenerate = true)
    var idRoom: Int = 0,

    /**
     * Identificador de la rutina como cadena de texto.
     */
    var id: String = "",

    /**
     * Tiempo de sueño en minutos.
     */
    var tiempoSueno: Int = 0,

    /**
     * Cantidad de calorías quemadas.
     */
    var caloriasQuemadas: Int = 0,

    /**
     * Número de pasos realizados.
     */
    var pasosRealizados: Int = 0,

    /**
     * Frecuencia cardiaca promedio en latidos por minuto.
     */
    var frecuenciaCardiaca: Float = 0f,

    /**
     * Nivel de oxígeno en sangre en porcentaje.
     */
    var nivelOxigenoSangre: Float = 0f,

    /**
     * Cantidad de agua consumida en mililitros.
     */
    var aguaconsumida: Int = 0,

    /**
     * Fecha en la que se está siguiendo la rutina.
     */
    var fechaSeguimiendo: String = "",

    /**
     * Fecha de la última modificación de la rutina.
     */
    var fechaUltimaModificacion: String = ""
)