package com.example.fitnesstrackerapp.clases.InfoMenu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class InfoResultado (
    val imagen:Int = 1,
    var titulo:String = "Metabolismo basal",
    var info:String = ""
)