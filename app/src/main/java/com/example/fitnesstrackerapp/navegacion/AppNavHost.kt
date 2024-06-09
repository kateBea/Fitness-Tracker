package com.example.fitnesstrackerapp.navegacion

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Addchart
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.fitnesstrackerapp.pantallas.PantallaBuscar
import com.example.fitnesstrackerapp.pantallas.PantallaInfoDiaria
import com.example.fitnesstrackerapp.pantallas.PantallaLogin
import com.example.fitnesstrackerapp.pantallas.VentanaRegister
import com.example.fitnesstrackerapp.pantallas.pantallaInformacion
import com.example.fitnesstrackerapp.pantallas.pantallaPrincipal
import com.example.fitnesstrackerapp.ui.theme.azul1
import com.example.fitnesstrackerapp.uiViewModel.InformacionViewModel

/**
 * Clase sellada que representa las diferentes pantallas de la aplicación.
 *
 * @property route La ruta asociada a la pantalla.
 */
sealed class Pantallas(var route: String) {
    /** Pantalla de inicio de sesión */
    data object Login : Pantallas("login")
    /** Pantalla de registro */
    data object Register : Pantallas("register")
    /** Pantalla de menú */
    data object Menu : Pantallas("menu")
    /** Pantalla de información */
    data object Info : Pantallas("info")
    /** Pantalla del diario */
    data object Diario : Pantallas("diario")
    /** Pantalla de búsqueda */
    data object Buscar : Pantallas("buscar")
}

/**
 * Función de navegación que configura las pantallas y la visibilidad de la barra de herramientas.
 *
 * @param navController Controlador de navegación de Jetpack Compose.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun navegacion(navController: NavHostController) {
    val showToolbar = remember { mutableStateOf(false) }
    hideOrShowToolbar(navController = navController, showToolbar = showToolbar)

    if (showToolbar.value) {
        scaffoldPantallas(navHostController = navController)
    } else {
        navegacionPantallas(navController = navController)
    }
}

/**
 * Función para mostrar u ocultar la barra de herramientas según la pantalla actual.
 *
 * @param navController Controlador de navegación de Jetpack Compose.
 * @param showToolbar Estado mutable que indica si la barra de herramientas debe mostrarse.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun hideOrShowToolbar(
    navController: NavHostController,
    showToolbar: MutableState<Boolean>
) {
    // Función que se llama cada vez que cambia el destino de la navegación
    navController.addOnDestinationChangedListener(NavController.OnDestinationChangedListener { controller, destination, arguments ->
        // Si estamos en la pantalla de login o registro, no mostrar la barra de herramientas; en todas las demás pantallas, sí.
        when (controller.currentDestination?.route) {
            Pantallas.Login.route -> {
                showToolbar.value = false
            }
            Pantallas.Register.route -> {
                showToolbar.value = false
            }
            else -> {
                showToolbar.value = true
            }
        }
    })
}

/**
 * Función que configura la navegación entre las diferentes pantallas de la aplicación.
 *
 * @param navController Controlador de navegación de Jetpack Compose.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun navegacionPantallas(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Pantallas.Login.route) {
        // Configura las diferentes pantallas y sus rutas
        composable(Pantallas.Login.route) {
            PantallaLogin(navController)
        }
        composable(Pantallas.Register.route) {
            VentanaRegister(navController)
        }
        composable(Pantallas.Menu.route) {
            pantallaPrincipal()
        }
        composable(Pantallas.Info.route) {
            pantallaInformacion()
        }
        composable(Pantallas.Diario.route) {
            PantallaInfoDiaria(navHostController = navController)
        }
        composable(
            route = "${Pantallas.Buscar.route}/{number}",
            arguments = listOf(navArgument("number") { type = NavType.IntType })
        ) {
            // Obtiene el argumento "number" de la ruta y muestra la pantalla de búsqueda
            val number = it.arguments?.getInt("number")
            PantallaBuscar(number ?: 0, navController)
        }
    }
}

/**
 * Función para configurar el scaffold de las pantallas.
 *
 * @param navHostController Controlador de navegación de Jetpack Compose.
 * @param informacionViewModel ViewModel para gestionar la información del usuario.
 */
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun scaffoldPantallas(
    navHostController: NavHostController,
    informacionViewModel: InformacionViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val nombre = informacionViewModel.username.collectAsState().value

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = azul1,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text(
                        nombre,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            tint = Color.White,
                            contentDescription = "Descripción localizada"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { contentPadding ->
        Column (
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
        ){
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .background(azul1)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.33f)
                        .clickable { navHostController.navigate("menu") },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Rounded.Person, contentDescription = "", tint = Color.White)
                }

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.5f)
                        .clickable { navHostController.navigate("info") },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Rounded.Addchart, contentDescription = "", tint = Color.White)
                }

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .clickable { navHostController.navigate("diario") },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Rounded.DateRange, contentDescription = "", tint = Color.White)
                }
            }
            // Configura la navegación entre las pantallas
            navegacionPantallas(navController = navHostController)
        }
    }
}