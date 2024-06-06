package com.example.fitnesstrackerapp.objetos.response

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
data class ResponseLogin(

    @SerializedName("data")
    var data:Data = Data(),

    @SerializedName("response_description")
    var responseDescription:String = "",

    @SerializedName("success")
    var success:Boolean = false,

)

enum class Sexo {
    Hombre,
    Mujer,
    Otro
}