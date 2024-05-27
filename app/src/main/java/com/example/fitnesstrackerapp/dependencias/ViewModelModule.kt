package com.example.fitnesstrackerapp.dependencias

import com.example.fitnesstrackerapp.uiViewModel.EventosViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Provides
    @Singleton
    fun provideMainViewModel(): EventosViewModel {
        return EventosViewModel()
    }
}