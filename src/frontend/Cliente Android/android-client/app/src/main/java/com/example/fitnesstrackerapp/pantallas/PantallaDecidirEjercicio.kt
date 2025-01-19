package com.example.fitnesstrackerapp.pantallas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.fitnesstrackerapp.ui.theme.colorBoton
import com.example.fitnesstrackerapp.uiViewModel.DecidirEjercicioViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun decidirEjercicio(decidirEjercicioViewModel: DecidirEjercicioViewModel = hiltViewModel(),navHostController: NavHostController){

    var nombre by remember { mutableStateOf("") }
    var duracion by remember { mutableStateOf("") }
    var kcal by remember { mutableStateOf("") }

    val done = decidirEjercicioViewModel.done.collectAsState().value

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(0.dp, 15.dp)
        ,horizontalAlignment = Alignment.CenterHorizontally){
        OutlinedTextField(
            value = nombre,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next),
            onValueChange = { nombre = it },
            label = { Text("Nombre del ejercicio")
            }
        )

        Spacer(modifier = Modifier.padding(0.dp,10.dp))
        OutlinedTextField(
            value = duracion,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next),
            onValueChange = { duracion = it },
            label = { Text("Duración del ejercicio")
            }
        )

        Spacer(modifier = Modifier.padding(0.dp,10.dp))
        OutlinedTextField(
            value = kcal,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done),
            onValueChange = { kcal = it },
            label = { Text("KiloCalorias Gastadas")
            }
        )

        Spacer(modifier = Modifier.padding(0.dp,10.dp))
        Button(
            onClick = {
                decidirEjercicioViewModel.introducirEjercicio(nombre,duracion,kcal)
            }, modifier = Modifier
                .padding(0.dp, 20.dp, 0.dp, 0.dp)
                .fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorBoton
            )
        ) {
            Text(text = "Agregar")
        }
    }

    if(done){
        navHostController.navigate("diario")
    }

}