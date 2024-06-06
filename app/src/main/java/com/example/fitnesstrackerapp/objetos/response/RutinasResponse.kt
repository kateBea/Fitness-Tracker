package com.example.fitnesstrackerapp.objetos.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fitnesstrackerapp.basedatos.entidades.Comida
import com.google.gson.annotations.SerializedName

class RutinasResponse (
    @SerializedName("id")
    var id:String = "",
    @SerializedName("tiempoDeSuenio")
    var tiempoSueno:Int = 0,
    @SerializedName("caloriasQuemadas")
    var caloriasQuemadas:Int = 0,
    @SerializedName("pasosRealizados")
    var pasosRealizados:Int = 0,
    @SerializedName("frecuenciaCardiaca")
    var frecuenciaCardiaca:Float = 0f,
    @SerializedName("nivelOxigenoSangre")
    var nivelOxigenoSangre:Float = 0f,
    @SerializedName("fechaSeguimiento")
    var fechaSeguimiendo:String = "",
    @SerializedName("fechaUltimaModificacion")
    var fechaUltimaModificacion:String = "",
    @SerializedName("comidasConsumidas")
    var comidas:List<Comida> = listOf()
)