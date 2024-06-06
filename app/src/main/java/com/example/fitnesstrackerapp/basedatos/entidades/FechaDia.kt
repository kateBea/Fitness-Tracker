package com.example.fitnesstrackerapp.basedatos.entidades

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = "fecha")
data class FechaDia(
    @PrimaryKey
    var id:Int = 1,
    var fecha:String = LocalDate.now().toString(),
    var existe:Boolean = false
)