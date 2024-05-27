package com.example.fitnesstrackerapp.clases.InfoMenu

import com.example.fitnesstrackerapp.uiViewModel.Acciones

data class InfoMenu (
    var info:String = "Texto 1",
    var valor:String = "120",
    var accion:Acciones = Acciones.CambiarEdad
){
}