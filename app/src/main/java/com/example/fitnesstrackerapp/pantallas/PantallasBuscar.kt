package com.example.fitnesstrackerapp.pantallas

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.fitnesstrackerapp.basedatos.entidades.Comida
import com.example.fitnesstrackerapp.objetos.response.responseAlimentos.InformacionComida
import com.example.fitnesstrackerapp.ui.theme.colorBoton
import com.example.fitnesstrackerapp.uiViewModel.BuscarViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PantallaBuscar(numero: Int = 0, navController: NavHostController, buscarViewModel: BuscarViewModel = hiltViewModel()) {
    var text by remember { mutableStateOf("") }

    val alimentos = buscarViewModel.alimentos.collectAsState().value
    val done = buscarViewModel.done.collectAsState().value

    val verticalScroll = rememberScrollState()
    
    Column (modifier = Modifier
        .fillMaxSize()
        .verticalScroll(verticalScroll)
        .padding(0.dp, 10.dp, 0.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally){

        OutlinedTextField(
            value = text,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done),
            onValueChange = { text = it },
            label = { Text("Meta el alimento (en inglés)")
            }
        )

        if (text.isNotEmpty()) {
            Button(
                onClick = {
                    buscarViewModel.buscarAlimentos(text)
                    text = ""
                }, modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorBoton
                )
            ) {
                Text(text = "Buscar")
            }
        }

        Spacer(modifier = Modifier.padding(0.dp,10.dp))
        if (alimentos.isNotEmpty()) {
            Divider()
            alimentos.forEach {
                filaAlimento(comida = it,buscarViewModel,numero)
            }
        }else{
            Text(text = "Sin resultados")
        }
    }

    if(done)
        navController.navigate("diario")
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun filaAlimento(comida: InformacionComida,buscarViewModel: BuscarViewModel,numero:Int){
    Column (modifier = Modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Max)
        .clickable { buscarViewModel.actualizar(comida, numero) }
        .padding(10.dp, 10.dp)){
        Row (modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f), horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = "${comida.comida.nombre}")
            Text(text = "${comida.comida.nutrientes.calorias} Kcal")
        }

        Row (modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom){
            Text(text = "${comida.comida.descripcion}", fontSize = 11.sp)
            Text(text = "C: ${comida.comida.nutrientes.carbohidratos}, P: ${comida.comida.nutrientes.proteinas}, G: ${comida.comida.nutrientes.grasas}")
        }
    }
    Divider()
}