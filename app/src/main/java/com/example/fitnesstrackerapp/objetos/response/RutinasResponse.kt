package com.example.fitnesstrackerapp.objetos.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fitnesstrackerapp.basedatos.entidades.Comida
import com.google.gson.annotations.SerializedName

/**
 * Clase de datos que devuelve las rutinas de la BBDD
 */
data class RutinasResponse (
    /**
     * Id de la rutina
     */
    @SerializedName("id")
    var id:String = "",

    /**
     * Tiempo de sueño en minutos
     */
    @SerializedName("tiempoDeSuenio")
    var tiempoSueno:Int = 0,

    /**
     * Calorias quemadas en ese dia
     */
    @SerializedName("caloriasQuemadas")
    var caloriasQuemadas:Int = 0,

    /**
     * Pasos que ha realizado en ese dia
     */
    @SerializedName("pasosRealizados")
    var pasosRealizados:Int = 0,

    /**
     * Frecuencia cardiaca en pulsaciones por minuto en ese dia
     */
    @SerializedName("frecuenciaCardiaca")
    var frecuenciaCardiaca:Float = 0f,


    /**
     * Nivel oxigeno en sangre en ese dia en ese dia
     */
    @SerializedName("nivelOxigenoSangre")
    var nivelOxigenoSangre:Float = 0f,

    /**
     * Fecha de la rutina
     */
    @SerializedName("fechaSeguimiento")
    var fechaSeguimiendo:String = "",

    /**
     * Fecha de la ultima modificacion
     */
    @SerializedName("fechaUltimaModificacion")
    var fechaUltimaModificacion:String = "",

    /**
     * Comidas consumidas en ese dia
     */
    @SerializedName("comidasConsumidas")
    var comidas:List<Comida> = listOf()
)