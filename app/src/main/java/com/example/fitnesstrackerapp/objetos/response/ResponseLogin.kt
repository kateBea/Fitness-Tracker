package com.example.fitnesstrackerapp.objetos.response

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
data class ResponseLogin(

    @SerializedName("response_description")
    var responseDescription:String = "",

    @SerializedName("success")
    var success:Boolean = false,

    @SerializedName("email")
    val email: String? = "",

    @SerializedName("name")
    val nombre: String? = "",

    @SerializedName("first_name")
    val apellido: String? = "",

    @SerializedName("second_name")
    val segundoApellido: String? = "",

    @SerializedName("token")
    val token: String? = "",

    @SerializedName("token_expiration_date")
    val tokenExpiration: LocalDateTime? = LocalDateTime.now(),

    @SerializedName("token_duration")
    val tokenDuration: Int = 0,

    @SerializedName("logged_at")
    val loggedAt: LocalDateTime? = LocalDateTime.now()
)

enum class Sexo {
    Hombre,
    Mujer
}