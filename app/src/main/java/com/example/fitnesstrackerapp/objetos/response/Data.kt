package com.example.fitnesstrackerapp.objetos.response

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
data class Data(

    @SerializedName("name")
    val nombre: String? = "",

    @SerializedName("username")
    val username:String? = "",

    @SerializedName("firstSurname")
    val apellido: String? = "",

    @SerializedName("secondSurname")
    val segundoApellido: String? = "",

    @SerializedName("token")
    val token: String? = "",

    @SerializedName("loginDate")
    val loginDate: String? = "",

    @SerializedName("tokenExpirationDate")
    val tokenExpirationDate: String? = "",

    @SerializedName("tokenDuration")
    val tokenDuration: Int? = 0
)