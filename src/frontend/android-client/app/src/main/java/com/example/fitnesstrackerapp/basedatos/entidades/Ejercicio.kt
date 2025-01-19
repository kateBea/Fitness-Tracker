package com.example.fitnesstrackerapp.basedatos.entidades

import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey

@Entity
data class Ejercicio(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    var nombre:String = "Cardio",
    var minutos:Int = 15,
    var Kcal:Int = 100,
    var fecha:String = ""
)