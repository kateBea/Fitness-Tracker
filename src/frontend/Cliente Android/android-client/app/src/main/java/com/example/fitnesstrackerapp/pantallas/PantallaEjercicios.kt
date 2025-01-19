package com.example.fitnesstrackerapp.pantallas

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnesstrackerapp.basedatos.entidades.Ejercicio
import com.example.fitnesstrackerapp.uiViewModel.EjerciciosTodosViewModel

@Composable
fun todosEjercicios(ejerciciosTodosViewModel: EjerciciosTodosViewModel = hiltViewModel()){

    val ejercicios = ejerciciosTodosViewModel.ejercicios.collectAsState().value
    val scroll = rememberScrollState()

    Column (modifier = Modifier
        .fillMaxHeight()
        .verticalScroll(scroll)
        ,horizontalAlignment = Alignment.CenterHorizontally){
        if (ejercicios.isNotEmpty()){
            ejercicios.forEach {
                infoEjercicio(it)
                Spacer(modifier = Modifier.padding(0.dp,3.dp))
            }
        }else{
            Text(text = "No hay alimentos")
        }
    }

}

@Composable
@Preview
fun infoEjercicio(ejercicio: Ejercicio = Ejercicio()){
    Row (modifier = Modifier
        .fillMaxWidth()
        .height(70.dp)){
        Row (modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight()){
            Column (modifier = Modifier
                .fillMaxSize()
                .padding(15.dp, 5.dp), verticalArrangement = Arrangement.SpaceBetween){
                Text(text = ejercicio.nombre, fontSize = 18.sp, fontWeight = FontWeight.Black)
                Text(text = "${ejercicio.minutos} minutos",fontSize = 18.sp)
            }
        }
        Row (modifier = Modifier
            .fillMaxSize()
            .padding(5.dp, 0.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically){
            Text(text = "${ejercicio.Kcal} Kcal",fontSize = 18.sp)
        }
    }
    Divider()
}