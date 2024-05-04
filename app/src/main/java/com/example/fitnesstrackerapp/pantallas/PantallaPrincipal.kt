package com.example.fitnesstrackerapp.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fitnesstrackerapp.clases.InfoMenu.InfoMenu
import com.example.fitnesstrackerapp.clases.InfoMenu.InfoResultado
import com.example.fitnesstrackerapp.ui.theme.colorPerfil

@Composable
fun pantallaPrincipal() {

    val infor = remember { mutableListOf(
        InfoMenu("Altura (cm)","150"),
        InfoMenu("Peso (kg)","69"),
        InfoMenu("Sexo","Hombre"),
        InfoMenu("Edad","23"),
        InfoMenu("Actividad","Bajo"),
        InfoMenu("Objetivo","Mantener Peso"),
        InfoMenu("Personalizar comidas","")
    ) }

    val resul = remember{
        mutableListOf(
            InfoResultado(),
            InfoResultado(),
            InfoResultado(),
            InfoResultado()
        )
    }

    val resul2 = remember{
        mutableListOf(
            InfoResultado(info = ""),
            InfoResultado(info = ""),
            InfoResultado(info = "")
        )
    }

    Column (modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())){
        filaInformacion("Mis datos")

        infor.forEach{
            filaPerfil(it.info,it.valor)
        }

        filaInformacion("Resultado")

        resul.forEach {
            filaResultado(it.imagen,it.color,it.titulo,it.info)
        }

        filaInformacion("Mi contenido")

        resul2.forEach {
            filaResultado(it.imagen,it.color,it.titulo,it.info)
        }

    }
}

@Composable
fun filaInformacion(texto:String = "Mis datos"){
    Row (modifier = Modifier
        .fillMaxWidth()
        .height(40.dp)
        .background(colorPerfil),
        verticalAlignment = Alignment.CenterVertically){

        Text(text = "$texto", modifier = Modifier.padding(15.dp,0.dp), fontSize = 20.sp)
    }
}

@Composable
@Preview
fun filaPerfil(texto:String = "Info1", texto2:String = "Infor 2"){
    Row (modifier = Modifier
        .fillMaxWidth()
        .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){

        Text(text = "$texto", fontSize = 18.sp, modifier = Modifier.padding(10.dp,0.dp))
        Text(text = "$texto2", fontSize = 18.sp,modifier = Modifier.padding(10.dp,0.dp))
    }
    Divider()
}

@Composable
@Preview
fun filaResultado(imagen:ImageVector = Icons.Rounded.LocalFireDepartment,
                  color:Color = Color.Yellow,
                  texto:String = "Metabolismo basal",
                  texto2:String = ""){
    Row (modifier = Modifier
        .fillMaxWidth()
        .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){

        Column (modifier = Modifier
            .fillMaxWidth(0.15f)
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Icon(imagen, contentDescription = "", tint =color)
        }

        Column (modifier = Modifier
            .fillMaxWidth(0.85f)
            .fillMaxHeight(), verticalArrangement = Arrangement.Center){
            Text(text = "$texto")
            if(texto2.isNotEmpty())
                Text(text = "$texto2", fontSize = 18.sp, fontWeight = FontWeight.Black)
        }

        Column (modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Icon(Icons.Rounded.ArrowForwardIos, contentDescription = "", tint = Color.Gray)
        }
    }
    Divider()
}