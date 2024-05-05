package com.example.fitnesstrackerapp.pantallas

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fitnesstrackerapp.ui.theme.colorPerfil

@Composable
fun PantallaInfoDiaria() {
    Column (modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())){
        filaDia()

        Row (modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(0.dp, 10.dp), horizontalArrangement = Arrangement.SpaceAround){
            Column (modifier = Modifier
                .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Carbohidratos", fontSize = 15.sp)
                Text(text = "60/300 g", fontWeight = FontWeight.Black)
            }
            Column (modifier = Modifier
                .fillMaxHeight(),horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Proteínas", fontSize = 15.sp)
                Text(text = "60/300 g", fontWeight = FontWeight.Black)
            }
            Column (modifier = Modifier
                .fillMaxHeight(),horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Grasas", fontSize = 15.sp)
                Text(text = "60/300 g", fontWeight = FontWeight.Black)
            }
        }

        Row (modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(0.dp, 10.dp), horizontalArrangement = Arrangement.SpaceAround){
            Column (modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "KiloCalorias", fontSize = 15.sp)
                Text(text = "240/2000 g", fontWeight = FontWeight.Black)
            }
        }

        filaInformacion("Desayuno")
        filaAlimento()
        filaAlimento()
        informacionComida()
        filaInformacion("Comida")
        informacionComida()
        filaInformacion("Cena")
        filaAlimento()
        informacionComida()
        filaInformacion("Agua")
        Row (modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
        , verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Text(text = "0/2500", fontSize = 30.sp, fontWeight = FontWeight.Black)
        }
    }
}

@Composable
@Preview
fun filaDia(){
    Row (modifier = Modifier
        .fillMaxWidth()
        .height(40.dp)
        .background(colorPerfil),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center){

        Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = "")
        Text(text = "Lunes, 29 Abr.", modifier = Modifier.padding(15.dp,0.dp), fontSize = 20.sp)
        Icon(Icons.Rounded.ArrowForwardIos, contentDescription = "")
    }
}

@Composable
@Preview
fun informacionComida(){
    Row (modifier = Modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Max)
        .padding(5.dp, 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){

        Column (modifier = Modifier.fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "0/61")
            Text(text = "Carbohidratos", fontSize = 13.sp)
        }

        Column (modifier = Modifier.fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "0/61")
            Text(text = "Proteinas", fontSize = 13.sp)
        }

        Column (modifier = Modifier.fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "0/61")
            Text(text = "Grasas", fontSize = 13.sp)
        }

        Column (modifier = Modifier.fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "0/610")
            Text(text = "KiloCalorias", fontSize = 13.sp)
        }
    }
}

@Composable
@Preview
fun filaAlimento(){
    Column (modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .padding(10.dp, 0.dp)){
        Row (modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f), horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = "Galletas")
            Text(text = "369")
        }

        Row (modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = "Cuétara, 8 galletas")
            Text(text = "C: 52.5, P: 10, G:0")
        }
    }
    Divider()
}
