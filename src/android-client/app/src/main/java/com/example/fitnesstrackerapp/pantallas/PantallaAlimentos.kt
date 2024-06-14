package com.example.fitnesstrackerapp.pantallas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnesstrackerapp.basedatos.entidades.Comida
import com.example.fitnesstrackerapp.objetos.response.responseTodosAlimentos.ComidaTodos
import com.example.fitnesstrackerapp.uiViewModel.AlimentosUserViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun mostrarTodosAlimentos(alimentosUserViewModel: AlimentosUserViewModel = hiltViewModel()){

    val comidas = alimentosUserViewModel.comidas.collectAsState().value
    val scroll = rememberScrollState()

    Column (modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scroll),
        horizontalAlignment = Alignment.CenterHorizontally){
        if(comidas.isNotEmpty()){
            comidas.forEach {
                filaAlimento(comida = it)
                Spacer(modifier = Modifier.padding(0.dp,3.dp))
            }
        }else{
            Text(text = "No hay alimentos")
        }
    }

}

@Composable
fun filaAlimento(comida: ComidaTodos){
    Column (modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .padding(10.dp, 0.dp)){
        Row (modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f), horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = "${comida.nombre}")
            Text(text = "${comida.calorias} Kcal")
        }

        Row (modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom){
            Text(text = "${comida.descripcion}", fontSize = 10.sp)
            Text(text = "C: ${comida.carbohidratos}, P: ${comida.proteinas}, G: ${comida.grasas}", fontSize = 11.sp)
        }
    }
    Divider()
}
