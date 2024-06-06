package com.example.fitnesstrackerapp.objetos.request

import com.google.gson.annotations.SerializedName

data class RutinaRequest(
    @SerializedName("tiempoDeSuenio") val tiempoDeSuenio: Int = 0,
    @SerializedName("caloriasQuemadas") val caloriasQuemadas: Int = 0,
    @SerializedName("pasosRealizados") val pasosRealizados: Int = 0,
    @SerializedName("frecuenciaCardiaca") val frecuenciaCardiaca: Int = 0,
    @SerializedName("nivelOxigenoSangre") val nivelOxigenoSangre: Int = 0
)