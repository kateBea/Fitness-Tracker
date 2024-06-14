package com.example.fitnesstrackerapp.basedatos.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Entidad que representa una comida en la base de datos.
 * La tabla correspondiente en la base de datos se llama "Comidas".
 */
@Entity(tableName = "Comidas")
data class Comida (
    /**
     * Identificador único de la comida. Se genera automáticamente.
     */
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    /**
     * Identificador de la comida (como cadena de texto).
     */
    @SerializedName("comidaId")
    var comidaId: String = "",

    /**
     * Nombre de la comida.
     */
    @SerializedName("nombre")
    var nombre: String = "",

    /**
     * Tipo de comida (desayuno, almuerzo, etc.).
     */
    @SerializedName("tipo")
    var tipo: TipoComida = TipoComida.DESAYUNO,

    /**
     * Hora de consumo de la comida.
     */
    @SerializedName("horaConsumo")
    var horaConsumo: String = "",

    /**
     * Orden del plato (primer plato, segundo plato, etc.).
     */
    @SerializedName("orden")
    var orden: Orden = Orden.PRIMER_PLATO,

    /**
     * Descripción de la comida.
     */
    @SerializedName("descripcion")
    var descripcion: String = "",

    /**
     * Cantidad de calorías en la comida.
     */
    @SerializedName("calorias")
    var calorias: Float = 0f,

    /**
     * Cantidad de proteínas en la comida.
     */
    @SerializedName("proteinas")
    var proteinas: Float = 0f,

    /**
     * Cantidad de grasas en la comida.
     */
    @SerializedName("grasas")
    var grasas: Float = 0f,

    /**
     * Cantidad de carbohidratos en la comida.
     */
    @SerializedName("carbohidratos")
    var carbohidratos: Float = 0f
)

/**
 * Enumeración que representa los diferentes tipos de comida.
 */
enum class TipoComida {
    DESAYUNO,
    MERIENDA_MATUTINA,
    ALMUERZO,
    MERIENDA_VESPERTINA,
    CENA
}

/**
 * Enumeración que representa el orden de los platos en una comida.
 */
enum class Orden {
    PRIMER_PLATO,
    SEGUNDO_PLATO,
    TERCER_PLATO,
    PLATO_UNICO
}