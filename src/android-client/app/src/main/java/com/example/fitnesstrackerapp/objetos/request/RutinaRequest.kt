package com.example.fitnesstrackerapp.objetos.request

import com.google.gson.annotations.SerializedName

/**
 * Clase de datos que representa una solicitud de rutina con información específica.
 * Contiene datos como tiempo de sueño, calorías quemadas, pasos realizados,
 * frecuencia cardiaca y nivel de oxígeno en sangre.
 */
data class RutinaRequest(
    /**
     * Tiempo de sueño del usuario en minutos.
     */
    @SerializedName("tiempoDeSuenio")
    val tiempoDeSuenio: Int = 0,

    /**
     * Calorías quemadas durante la rutina.
     */
    @SerializedName("caloriasQuemadas")
    val caloriasQuemadas: Int = 0,

    /**
     * Número de pasos realizados durante la rutina.
     */
    @SerializedName("pasosRealizados")
    val pasosRealizados: Int = 0,

    /**
     * Frecuencia cardiaca promedio durante la rutina.
     */
    @SerializedName("frecuenciaCardiaca")
    val frecuenciaCardiaca: Int = 0,

    /**
     * Nivel de oxígeno en sangre durante la rutina.
     */
    @SerializedName("nivelOxigenoSangre")
    val nivelOxigenoSangre: Int = 0
)