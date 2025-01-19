package com.example.fitnesstrackerapp.dependencias

import com.example.fitnesstrackerapp.uiViewModel.EventosViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Módulo de Dagger Hilt para proporcionar instancias de ViewModel.
 */
@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    /**
     * Provee una instancia del ViewModel principal.
     *
     * @return Una instancia del ViewModel principal.
     */
    @Provides
    @Singleton
    fun provideMainViewModel(): EventosViewModel {
        return EventosViewModel()
    }
}