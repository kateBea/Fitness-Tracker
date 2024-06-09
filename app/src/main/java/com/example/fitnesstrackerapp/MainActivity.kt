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

/**
 * MainActivity es el punto de entrada principal de la aplicación Android.
 * Está anotada con @AndroidEntryPoint para habilitar la inyección de dependencias usando Hilt.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * ViewModel para gestionar los datos relacionados con la UI en el ciclo de vida de la actividad.
     */
    private val eventosViewModel: EventosViewModel by viewModels()

    /**
     * Llamado cuando la actividad se está iniciando. Aquí es donde debe ir la mayor parte de la inicialización.
     *
     * @param savedInstanceState Si la actividad está siendo re-inicializada después de haber sido
     *                           previamente cerrada, entonces este Bundle contiene los datos más
     *                           recientes proporcionados en {@link #onSaveInstanceState(Bundle)}.
     *                           Nota: En caso contrario, es null.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val estado = eventosViewModel.uiState.collectAsState().value

            FitnessTrackerAppTheme {
                // Un contenedor de superficie usando el color de 'background' del tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navegacion(navController)

                    when (estado) {
                        EventosUIState.Cargando -> { cargando() }
                        EventosUIState.Done -> { /* No se necesita acción */ }
                        is EventosUIState.Error -> {
                            MensajeError(texto = estado.texto) {
                                eventosViewModel.setState(EventosUIState.Done)
                            }
                        }
                    }
                }
            }
        }
    }
}