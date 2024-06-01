package com.example.fitnesstrackerapp.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fitnesstrackerapp.R
import com.example.fitnesstrackerapp.ui.theme.azul1
import com.example.fitnesstrackerapp.ui.theme.azul2
import com.example.fitnesstrackerapp.ui.theme.colorBoton
import com.example.fitnesstrackerapp.utilidades.BotonLogin

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VentanaRegister(navController: NavHostController) {
    val brush = Brush.linearGradient(listOf(azul2, azul1))
    val image: Painter = painterResource(id = R.drawable.grupo)
    val fuente = FontFamily(Font(R.font.extralight))

    var textoEmail =  remember { mutableStateOf("") }
    var textoPass = remember { mutableStateOf("") }

    var firstTimeButon by remember { mutableStateOf(false) }
    val (focusRequester) = FocusRequester.createRefs()


    Column (modifier = Modifier
        .background(brush)
        .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){

        Column (modifier = Modifier
            .fillMaxHeight(0.4f)
            .fillMaxWidth()
            , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            Image(painter = image, contentDescription = "Logo",modifier = Modifier.size(120.dp))
            Text(text = "Nombre", color = Color.White, fontSize = 45.sp, fontFamily = fuente)
        }


        Column (modifier = Modifier
            .fillMaxWidth(0.8f)
            .fillMaxHeight(0.7f)
            ,horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top){

            BotonLogin(
                campo = textoEmail,
                firstTimeButton = firstTimeButon,
                labelTexto = "Usuario",
                textoError = "Escriba el usuario",
                accountCircle = Icons.Rounded.Person,
                focusRequester = focusRequester,
            )

            Spacer(modifier = Modifier.padding(0.dp,10.dp))

            BotonLogin(
                campo = textoEmail,
                firstTimeButton = firstTimeButon,
                labelTexto = "Email",
                textoError = "Escriba el email",
                accountCircle = Icons.Rounded.Email,
                focusRequester = focusRequester,
            )

            Spacer(modifier = Modifier.padding(0.dp,10.dp))

            BotonLogin(
                campo = textoPass,
                firstTimeButton = firstTimeButon,
                labelTexto = "Contraseña",
                textoError = "Escriba la contraseña",
                accountCircle = Icons.Rounded.Lock,
                focusRequester = focusRequester
            )
        }

        Column (modifier = Modifier.fillMaxSize()){
            Column (modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally){
                ElevatedButton(
                    onClick = {
                        firstTimeButon = true
                        navController.navigate("menu")
                    }, modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth(0.8f)
                        .padding(15.dp, 0.dp),
                    shape = RoundedCornerShape(40.dp, 0.dp, 40.dp, 0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoton
                    )
                ) {
                    Text("Registrar", fontSize = 25.sp)
                }
            }
            Column (modifier = Modifier.fillMaxSize()
                , horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom){
                Row (modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center){
                    Text(text = "¿Ya tienes cuenta?", modifier = Modifier.padding(0.dp,10.dp)
                        , fontSize = 17.sp, color = Color.White)
                    Text(text = " Entra aquí", modifier = Modifier.padding(0.dp,10.dp).clickable { navController.navigate("login") }
                        , fontSize = 17.sp, color = Color.White, fontWeight = FontWeight.Black)
                }
            }
        }
    }
}