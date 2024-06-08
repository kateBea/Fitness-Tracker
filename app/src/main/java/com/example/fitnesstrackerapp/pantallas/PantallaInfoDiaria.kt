package com.example.fitnesstrackerapp.pantallas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.fitnesstrackerapp.basedatos.entidades.Comida
import com.example.fitnesstrackerapp.basedatos.entidades.TipoComida
import com.example.fitnesstrackerapp.ui.theme.colorPerfil
import com.example.fitnesstrackerapp.uiViewModel.AlimentosViewModel
import java.util.stream.Collectors

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PantallaInfoDiaria(alimentosViewModel: AlimentosViewModel = hiltViewModel(),navHostController: NavHostController) {
    val comidas = alimentosViewModel.comidas.collectAsState().value

    val kcal = alimentosViewModel.kcal.collectAsState().value
    val proteinas = alimentosViewModel.proteinas.collectAsState().value
    val grasas = alimentosViewModel.grasas.collectAsState().value
    val carbohidratos = alimentosViewModel.carbohidratos.collectAsState().value
    val agua = alimentosViewModel.agua.collectAsState().value
    val aguaReq = alimentosViewModel.aguaReq.collectAsState().value


    Column (modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())){
        filaDia(alimentosViewModel)

        Row (modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(0.dp, 10.dp), horizontalArrangement = Arrangement.SpaceAround){
            Column (modifier = Modifier
                .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Carbohidratos", fontSize = 15.sp)
                Text(text = "${comidas.stream().map { it.carbohidratos }.collect(Collectors.toList()).sum().toInt()}/${carbohidratos.toInt()} g", fontWeight = FontWeight.Black)
            }
            Column (modifier = Modifier
                .fillMaxHeight(),horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Proteínas", fontSize = 15.sp)
                Text(text = "${comidas.stream().map { it.proteinas }.collect(Collectors.toList()).sum().toInt()}/${proteinas.toInt()} g", fontWeight = FontWeight.Black)
            }
            Column (modifier = Modifier
                .fillMaxHeight(),horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Grasas", fontSize = 15.sp)
                Text(text = "${comidas.stream().map { it.carbohidratos }.collect(Collectors.toList()).sum().toInt()}/${grasas.toInt()} g", fontWeight = FontWeight.Black)
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
                Text(text = "${comidas.stream().map { it.calorias }.collect(Collectors.toList()).sum().toInt()}/${kcal.toInt()} g", fontWeight = FontWeight.Black)
            }
        }

        filaAlimentacion(texto = "Desayuno", lista = comidas.stream().filter { it.tipo == TipoComida.DESAYUNO}.collect(Collectors.toList()),navHostController,proteinas,carbohidratos,grasas)
        filaAlimentacion(
            texto = "Almuerzo",
            lista = comidas.stream().filter { it.tipo == TipoComida.MERIENDA_MATUTINA}.collect(Collectors.toList()),
            navHostController,
            proteinas,
            carbohidratos,
            grasas
        )
        filaAlimentacion(
            texto = "Comida",
            lista = comidas.stream().filter { it.tipo == TipoComida.ALMUERZO}.collect(Collectors.toList()),
            navHostController,
            proteinas,
            carbohidratos,
            grasas
        )
        filaAlimentacion(
            texto = "Merienda",
            lista = comidas.stream().filter { it.tipo == TipoComida.MERIENDA_VESPERTINA}.collect(Collectors.toList()),
            navHostController,
            proteinas,
            carbohidratos,
            grasas
        )
        filaAlimentacion(
            texto = "Cena",
            lista = comidas.stream().filter { it.tipo == TipoComida.CENA}.collect(Collectors.toList()),
            navHostController,
            proteinas,
            carbohidratos,
            grasas
        )

        filaInformacion("Agua")

        Row (modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
        , verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = "", modifier = Modifier.clickable {alimentosViewModel.actualizarAgua(false)})
            Text(text = "$agua/$aguaReq", fontSize = 30.sp, fontWeight = FontWeight.Black, modifier = Modifier.padding(25.dp,0.dp))
            Icon(Icons.Rounded.ArrowForwardIos, contentDescription = "", modifier = Modifier.clickable {alimentosViewModel.actualizarAgua(true)})
        }
    }
}

@Composable
fun filaAlimentacion(
    texto: String,
    lista: List<Comida>,
    navHostController: NavHostController,
    proteinas: Float,
    carbohidratos: Float,
    grasas: Float
){
    filaInformacion(texto)
    lista.forEach { filaAlimento(it) }
    Row (modifier = Modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Max)
        .padding(0.dp, 10.dp), horizontalArrangement = Arrangement.SpaceAround){
        Column (modifier = Modifier
            .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Carbohidratos", fontSize = 15.sp)
            Text(text = "${lista.stream().map { it.carbohidratos }.collect(Collectors.toList()).sum().toInt()}/${(carbohidratos/5).toInt()} g", fontWeight = FontWeight.Black)
        }
        Column (modifier = Modifier
            .fillMaxHeight(),horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Proteínas", fontSize = 15.sp)
            Text(text = "${lista.stream().map { it.proteinas }.collect(Collectors.toList()).sum().toInt()}/${(proteinas/5).toInt()} g", fontWeight = FontWeight.Black)
        }
        Column (modifier = Modifier
            .fillMaxHeight(),horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Grasas", fontSize = 15.sp)
            Text(text = "${lista.stream().map { it.carbohidratos }.collect(Collectors.toList()).sum().toInt()}/${(grasas/5).toInt()} g", fontWeight = FontWeight.Black)
        }
    }
    Divider()
    filaAniadir(sacarComida(texto),navHostController)
}

fun sacarComida(texto: String):Int{
    var lista = listOf("Desayuno","Almuerzo","Comida","Merienda","Cena")
    return lista.indexOf(texto)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun filaDia(alimentosViewModel: AlimentosViewModel) {
    val fecha = alimentosViewModel.fecha.collectAsState().value

    Row (modifier = Modifier
        .fillMaxWidth()
        .height(40.dp)
        .background(colorPerfil),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center){

        Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = "", modifier = Modifier.clickable {
            alimentosViewModel.diaMenos()
        })
        Text(text = "${fecha.dayOfWeek}, ${fecha.dayOfMonth} ${fecha.month}.", modifier = Modifier.padding(15.dp,0.dp), fontSize = 20.sp)
        Icon(Icons.Rounded.ArrowForwardIos, contentDescription = "", modifier = Modifier.clickable {
            alimentosViewModel.diaMas()
        })
    }
}

@Composable
fun filaAniadir(sacarComida: Int, navHostController: NavHostController) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .clickable { navHostController.navigate("buscar/$sacarComida") }
        .height(40.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
        Icon(Icons.Rounded.AddCircle, contentDescription = "", tint = Color.Cyan)
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
fun filaAlimento(comida: Comida){
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
            Text(text = "${comida.descripcion}", fontSize = 11.sp)
            Text(text = "C: ${comida.carbohidratos}, P: ${comida.proteinas}, G: ${comida.grasas}")
        }
    }
    Divider()
}
