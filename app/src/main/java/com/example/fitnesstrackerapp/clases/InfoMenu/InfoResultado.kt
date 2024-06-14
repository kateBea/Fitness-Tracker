package com.example.fitnesstrackerapp.clases.InfoMenu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Clase de datos que representa la información de los resultados de la informacion principal.
 * Contiene una imagen asociada, un título y detalles informativos.
 */
data class InfoResultado (
    /**
     * Identificador de la imagen asociada al resultado.
     */
    val imagen: Int = 1,

    /**
     * Título del resultado.
     */
    var titulo: String = "Metabolismo basal",

    /**
     * Información detallada del resultado.
     */
    var info: String = "",

    /**
     * Ruta de navegacion de la info.
     */
    var ruta: String = ""
)