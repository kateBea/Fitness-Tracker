package com.example.fitnesstrackerapp.basedatos.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity( tableName = "Rutinas")
data class Rutina (
    @PrimaryKey(autoGenerate = true)
    var idRoom:Int = 0,
    var id:String = "",
    var tiempoSueno:Int = 0,
    var caloriasQuemadas:Int = 0,
    var pasosRealizados:Int = 0,
    var frecuenciaCardiaca:Float = 0f,
    var nivelOxigenoSangre:Float = 0f,
    var aguaconsumida:Int = 0,
    var fechaSeguimiendo:String = "",
    var fechaUltimaModificacion:String = "",
)