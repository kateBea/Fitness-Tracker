package com.example.fitnesstrackerapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstrackerapp.navegacion.navegacion
import com.example.fitnesstrackerapp.ui.theme.FitnessTrackerAppTheme
import com.example.fitnesstrackerapp.uiViewModel.EventosUIState
import com.example.fitnesstrackerapp.uiViewModel.EventosViewModel
import com.example.fitnesstrackerapp.utilidades.MensajeError
import com.example.fitnesstrackerapp.utilidades.cargando
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val eventosViewModel: EventosViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val estado = eventosViewModel.uiState.collectAsState().value

            FitnessTrackerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navegacion(navController)

                    when(estado){
                        EventosUIState.Cargando -> {cargando()}
                        EventosUIState.Done -> {}
                        is EventosUIState.Error -> {MensajeError(texto = estado.texto){eventosViewModel.setState(EventosUIState.Done)}}
                    }
                }
            }
        }
    }
}