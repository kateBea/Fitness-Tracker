package com.example.fitnesstrackerapp.pantallas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.DoNotDisturb
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnesstrackerapp.R
import com.example.fitnesstrackerapp.clases.InfoMenu.InfoResultado
import com.example.fitnesstrackerapp.ui.theme.colorPerfil
import com.example.fitnesstrackerapp.uiViewModel.Acciones
import com.example.fitnesstrackerapp.uiViewModel.PantallaPrincipalViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun pantallaPrincipal(pantallaPrincipalViewModel: PantallaPrincipalViewModel = hiltViewModel()) {

    val informacion = pantallaPrincipalViewModel.menuDatosUsuario.collectAsState().value
    val datos = pantallaPrincipalViewModel.datos.collectAsState().value

    val resul2 = remember{
        mutableListOf(
            InfoResultado(imagen = R.drawable.alimentos, titulo = "Mis alimentos"),
            InfoResultado(imagen = R.drawable.batidor,titulo = "Mis recetas"),
            InfoResultado(imagen = R.drawable.rutina,titulo = "Mis ejercicios")
        )
    }

    if(informacion.isNotEmpty()){
        Column (modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())){
            filaInformacion("Mis datos")

            informacion.forEach{
                filaPerfil(it.info,it.valor,it.accion,pantallaPrincipalViewModel)
            }

            filaInformacion("Resultado")

            datos.forEach {
                FilaResultado(it.imagen,it.titulo,it.info)
            }

            filaInformacion("Mi contenido")

            resul2.forEach {
                FilaResultado(it.imagen,it.titulo,it.info)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bottomSheet(
    showBottomSheet: MutableState<Boolean>,
    sheetState: SheetState,
    texto: String,
    accion: Acciones,
    pantallaPrincipalViewModel: PantallaPrincipalViewModel
){
    ModalBottomSheet(
        onDismissRequest = { showBottomSheet.value = false },
        sheetState = sheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        var valor = remember { mutableStateOf("") }

        Box(
            modifier = Modifier
                .height(225.dp)
                .fillMaxWidth()
                .padding(10.dp, 0.dp)
        ) {
            Column (modifier = Modifier.fillMaxSize()){
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 0.dp), horizontalArrangement = Arrangement.SpaceBetween){
                    Icon(Icons.Rounded.DoNotDisturb, contentDescription = "", tint = Color.Gray, modifier = Modifier
                        .size(30.dp)
                        .clickable { showBottomSheet.value = false })
                    Icon(Icons.Rounded.Done, contentDescription = "", tint = Color.Blue,modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            realizarAccion(valor.value, accion, pantallaPrincipalViewModel)
                            showBottomSheet.value = false
                        })
                }

                if(accion != Acciones.CambiarSexo){
                    Row (modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 20.dp, 0.dp, 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                        Text(text = "Modificar $texto", fontSize = 20.sp, fontWeight = FontWeight.Black)
                    }
                    Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                        OutlinedTextField(
                            value = valor.value,
                            onValueChange = { valor.value = it },
                            label = { Text(texto) },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Number
                            )
                        )
                    }
                }else{
                    val radioOptions = listOf("Hombre", "Mujer")
                    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1] ) }
                    valor.value = selectedOption
                    Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                        radioOptions.forEach { text ->
                            Row(
                                Modifier
                                    .fillMaxWidth(0.6f)
                                    .selectable(
                                        selected = (text == selectedOption),
                                        onClick = {
                                            onOptionSelected(text)
                                            valor.value = selectedOption
                                        }
                                    ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = (text == selectedOption),
                                    onClick = { onOptionSelected(text)
                                        valor.value = selectedOption}
                                )
                                Text(
                                    text = text
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun realizarAccion(
    valor: String,
    accion: Acciones,
    pantallaPrincipalViewModel: PantallaPrincipalViewModel
) {
    when(accion){
        Acciones.CambiarPeso -> {pantallaPrincipalViewModel.cambiarPeso(valor)}
        Acciones.CambiarAltura -> {pantallaPrincipalViewModel.cambiarAltura(valor)}
        Acciones.CambiarSexo -> {pantallaPrincipalViewModel.cambiarSexo(valor)}
        Acciones.CambiarEdad -> {}
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

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun filaPerfil(
    texto: String = "Info1",
    texto2: String = "Infor 2",
    accion: Acciones,
    pantallaPrincipalViewModel: PantallaPrincipalViewModel
){
    val showBottomSheet = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    Row (modifier = Modifier
        .fillMaxWidth()
        .height(40.dp)
        .clickable { showBottomSheet.value = true },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){

        Text(text = texto, fontSize = 18.sp, modifier = Modifier.padding(10.dp,0.dp))
        Text(text = texto2, fontSize = 18.sp,modifier = Modifier.padding(10.dp,0.dp))
    }
    Divider()

    if(showBottomSheet.value && accion != Acciones.CambiarEdad)
        bottomSheet(showBottomSheet,sheetState,texto,accion,pantallaPrincipalViewModel)

}

@Composable
fun FilaResultado(imagen:Int,
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
            Image(painter = painterResource(id = imagen), contentDescription = "", modifier = Modifier.size(25.dp))
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