package com.example.fitnesstrackerapp.dependencias

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Clase de aplicación base que inicializa Hilt para la inyección de dependencias.
 */
@HiltAndroidApp
class AplicacionBase : Application() {}