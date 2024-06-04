package com.example.fitnesstrackerapp.dependencias

import android.content.Context
import androidx.room.Room
import com.example.fitnesstrackerapp.basedatos.bbdd.BaseDatos
import com.example.fitnesstrackerapp.basedatos.dao.UsuarioDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideYourDatabase(
        context: Context
    ): BaseDatos {
        return synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                BaseDatos::class.java,
                "baseDatos"
            ).fallbackToDestructiveMigration().build()
            instance
        }
    }

    @Provides
    @Singleton
    fun provideYourDao(db: BaseDatos): UsuarioDao = db.userDao()

}