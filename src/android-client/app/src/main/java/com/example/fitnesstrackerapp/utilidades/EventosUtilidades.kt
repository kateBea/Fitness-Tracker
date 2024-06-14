package com.example.fitnesstrackerapp.utilidades

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.fitnesstrackerapp.R
import kotlinx.coroutines.Job

/**
 * Composable utilizado para mostrar un indicador de carga en pantalla completa.
 * Este composable cubre toda la pantalla con un fondo semi-transparente y muestra
 * un indicador de carga animado en el centro.
 */
@Composable
fun cargando(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(com.example.fitnesstrackerapp.ui.theme.cargando) // Fondo semi-transparente
            .zIndex(1f)
            .clickable(enabled = false) {} // Captura los toques y no los deja pasar
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedPreloader(modifier = Modifier.size(100.dp), R.raw.animacioncargando, 1.5f)
        }
    }
}

/**
 * Composable para mostrar un mensaje de error en un modal bottom sheet.
 *
 * @param texto Texto a mostrar como mensaje de error.
 * @param onDismiss Función lambda para manejar el evento de cierre del modal.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MensajeError(texto:String = "",onDismiss: () -> Job){
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Box(
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .padding(10.dp, 0.dp)
        ) {
            Column (modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = texto,maxLines = 1, modifier = Modifier.basicMarquee(), fontWeight = FontWeight.Black)
                Spacer(modifier = Modifier.padding(10.dp))
                Button(onClick = { onDismiss() }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                ), shape = RoundedCornerShape(4.dp)
                ) {
                    Text(text = "Aceptar")
                }
            }
        }
    }
}