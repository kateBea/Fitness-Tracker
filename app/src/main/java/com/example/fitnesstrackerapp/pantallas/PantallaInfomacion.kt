package com.example.fitnesstrackerapp.pantallas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.fitnesstrackerapp.uiViewModel.InformacionVistaViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun pantallaInformacion(informacionVistaViewModel: InformacionVistaViewModel = hiltViewModel()) {
    val info = remember { mutableListOf("Tipo de dieta","Editar distribucion","Otros objetivos nutricionales") }

    val kcal = informacionVistaViewModel.kcal.collectAsState().value
    val proteinas = informacionVistaViewModel.proteinas.collectAsState().value
    val grasas = informacionVistaViewModel.grasas.collectAsState().value
    val carbohidratos = informacionVistaViewModel.carbohidratos.collectAsState().value

    Column (modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())){
        filaInformacion("Requerimiento calórico Diario")

        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Card(modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(40.dp)) {
                Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center){
                    Text(text = "$kcal KCAL", fontSize = 20.sp, fontWeight = FontWeight.Black)
                }
            }
        }

        filaInformacion("Distribución de macronutrientes")

        Row (modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(0.dp, 10.dp), horizontalArrangement = Arrangement.SpaceAround){
            Column (modifier = Modifier
                .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Carbohidratos", fontSize = 15.sp)
                Text(text = "${carbohidratos.toInt()} g", fontWeight = FontWeight.Black)
            }
            Column (modifier = Modifier
                .fillMaxHeight(),horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Proteínas", fontSize = 15.sp)
                Text(text = "${proteinas.toInt()} g", fontWeight = FontWeight.Black)
            }
            Column (modifier = Modifier
                .fillMaxHeight(),horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Grasas", fontSize = 15.sp)
                Text(text = "${grasas.toInt()} g", fontWeight = FontWeight.Black)
            }
        }

        Divider()
        info.forEach {
            filaNutrientres(it)
        }

        filaInformacion("Distribución de comidas")

        Row (modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(0.dp, 10.dp), horizontalArrangement = Arrangement.SpaceAround){
            Column (modifier = Modifier
                .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Carbohidratos", fontSize = 15.sp)
                Text(text = "${(carbohidratos/5).toInt()} g", fontWeight = FontWeight.Black)
            }
            Column (modifier = Modifier
                .fillMaxHeight(),horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Proteínas", fontSize = 15.sp)
                Text(text = "${(proteinas/5).toInt()} g", fontWeight = FontWeight.Black)
            }
            Column (modifier = Modifier
                .fillMaxHeight(),horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Grasas", fontSize = 15.sp)
                Text(text = "${(grasas/5).toInt()} g", fontWeight = FontWeight.Black)
            }
        }

        Divider()
        filaNutrientres("Editar distribucion de comidas")

    }
}

@Composable
@Preview
fun filaNutrientres(texto:String = "Metabolismo basal"){
    Row (modifier = Modifier
        .fillMaxWidth()
        .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){

        Column (modifier = Modifier
            .fillMaxWidth(0.85f)
            .fillMaxHeight(), verticalArrangement = Arrangement.Center){
            Text(text = "$texto", Modifier.padding(10.dp,0.dp))
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