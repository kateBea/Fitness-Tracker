package com.example.fitnesstrackerapp.basedatos.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity( tableName = "Comidas")
data class Comida (
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    @SerializedName("comidaId")
    var comidaId:String = "",
    @SerializedName("nombre")
    var nombre:String = "",
    @SerializedName("tipo")
    var tipo:TipoComida = TipoComida.DESAYUNO,
    @SerializedName("horaConsumo")
    var horaConsumo:String = "",
    @SerializedName("orden")
    var orden:Orden = Orden.PRIMER_PLATO,
    @SerializedName("descripcion")
    var descripcion:String = "",
    @SerializedName("calorias")
    var calorias:Float = 0f,
    @SerializedName("proteinas")
    var proteinas:Float = 0f,
    @SerializedName("grasas")
    var grasas:Float = 0f,
    @SerializedName("carbohidratos")
    var carbohidratos:Float = 0f,
)

enum class TipoComida {
    DESAYUNO,
    MERIENDA_MATUTINA,
    ALMUERZO,
    MERIENDA_VESPERTINA,
    CENA
}

enum class Orden{
    PRIMER_PLATO,
    SEGUNDO_PLATO,
    TERCER_PLATO,
    PLATO_UNICO
}