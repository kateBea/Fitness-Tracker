package com.example.fitnesstrackerapp.dependencias

import android.content.Context
import androidx.room.Room
import com.example.fitnesstrackerapp.basedatos.bbdd.BaseDatos
import com.example.fitnesstrackerapp.basedatos.dao.ComidaDao
import com.example.fitnesstrackerapp.basedatos.dao.FechaDao
import com.example.fitnesstrackerapp.basedatos.dao.RutinaDao
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

    @Provides
    @Singleton
    fun provideFechaDao(db:BaseDatos):FechaDao = db.fechaDao()

    @Provides
    @Singleton
    fun providesComidaDao(db: BaseDatos): ComidaDao = db.comidaDao()

    @Provides
    @Singleton
    fun providesRutinaDao(db: BaseDatos): RutinaDao = db.rutinaDao()

}