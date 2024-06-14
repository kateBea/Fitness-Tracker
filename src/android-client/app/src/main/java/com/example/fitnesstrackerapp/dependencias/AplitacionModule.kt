package com.example.fitnesstrackerapp.dependencias

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Módulo de Dagger Hilt para proporcionar instancias de contexto de aplicación.
 */
@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    /**
     * Provee el contexto de la aplicación.
     *
     * @param application La instancia de la aplicación.
     * @return El contexto de la aplicación.
     */
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}