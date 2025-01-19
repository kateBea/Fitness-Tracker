package com.example.fitnesstrackerapp.clases.InfoMenu

import com.example.fitnesstrackerapp.uiViewModel.Acciones

/**
 * Clase de datos que representa la informacion del menu principal a mostrar.
 * Contiene un texto de información, un valor asociado y una acción a realizar.
 */
data class InfoMenu (
    /**
     * Texto descriptivo de la información del menú.
     */
    var info: String = "Texto 1",

    /**
     * Valor asociado a la información del menú.
     */
    var valor: String = "120",

    /**
     * Acción a realizar relacionada con la información del menú.
     */
    var accion: Acciones = Acciones.CambiarEdad
)