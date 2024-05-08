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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitnesstrackerapp.pantallas.PantallaInfoDiaria
import com.example.fitnesstrackerapp.pantallas.PantallaLogin
import com.example.fitnesstrackerapp.pantallas.VentanaRegister
import com.example.fitnesstrackerapp.pantallas.pantallaInformacion
import com.example.fitnesstrackerapp.pantallas.pantallaPrincipal
import com.example.fitnesstrackerapp.ui.theme.azul1
import com.example.fitnesstrackerapp.uiViewModel.ViewModelFitness

sealed class Pantallas(var route:String){
    data object Login : Pantallas("login")
    data object Register: Pantallas("register")
    data object Menu : Pantallas("menu")
    data object Info : Pantallas("info")
    data object Diario: Pantallas("diario")
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun navegacion(navController: NavHostController, ViewModel: ViewModelFitness) {
    val showToolbar = remember {mutableStateOf(false)}
    hideOrShowToolbar(navController = navController, showToolbar = showToolbar)

    if(showToolbar.value){
        scaffoldPantallas(navHostController = navController, ViewModel)
    }else{
        navegacionPantallas(navController = navController,ViewModel)
    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun hideOrShowToolbar(
    navController: NavHostController,
    showToolbar: MutableState<Boolean>
){
    // Funcion que devuelve el destino actual cada vez que cambia
    navController.addOnDestinationChangedListener(NavController.OnDestinationChangedListener { controller, destination, arguments ->
        // Si estamos en el login, no mostrar el menú, en todas las demas pantallas sí
        when(controller.currentDestination?.route){
            Pantallas.Login.route ->{
                showToolbar.value = false
            }
            Pantallas.Register.route ->{
                showToolbar.value = false
            }
            else ->{
                showToolbar.value = true
            }
        }
    })
}

@Composable
fun navegacionPantallas(navController: NavHostController, ViewModel: ViewModelFitness){
    NavHost(navController = navController, startDestination = Pantallas.Login.route){
        composable(Pantallas.Login.route){
            PantallaLogin(navController,ViewModel)
        }
        composable(Pantallas.Register.route){
            VentanaRegister(navController)
        }
        composable(Pantallas.Menu.route){
            pantallaPrincipal()
        }
        composable(Pantallas.Info.route){
            pantallaInformacion()
        }
        composable(Pantallas.Diario.route){
            PantallaInfoDiaria()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun scaffoldPantallas(navHostController: NavHostController, ViewModel: ViewModelFitness){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

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
                        "Nombre",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            tint = Color.White,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { it ->
        Column (modifier = Modifier
            .padding(it)
            .fillMaxSize()){
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
            navegacionPantallas(navController = navHostController, ViewModel = ViewModel)
        }
    }
}