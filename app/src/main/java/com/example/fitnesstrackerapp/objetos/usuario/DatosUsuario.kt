package com.example.fitnesstrackerapp.objetos.usuario

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName

@RequiresApi(Build.VERSION_CODES.O)
data class DatosUsuario(
    @SerializedName("data")
    val usuario: Usuario = Usuario(),
    @SerializedName("responseDescription")
    val responseDescription:String = "",
    @SerializedName("success")
    val success:Boolean = false
)