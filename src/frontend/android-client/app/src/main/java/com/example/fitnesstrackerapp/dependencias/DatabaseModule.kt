package com.example.fitnesstrackerapp.dependencias

import android.content.Context
import androidx.room.Room
import com.example.fitnesstrackerapp.basedatos.bbdd.BaseDatos
import com.example.fitnesstrackerapp.basedatos.dao.ComidaDao
import com.example.fitnesstrackerapp.basedatos.dao.EjercicioDao
import com.example.fitnesstrackerapp.basedatos.dao.FechaDao
import com.example.fitnesstrackerapp.basedatos.dao.RutinaDao
import com.example.fitnesstrackerapp.basedatos.dao.UsuarioDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Módulo de Dagger Hilt para proporcionar instancias de base de datos y DAOs.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provee la instancia de la base de datos.
     *
     * @param context El contexto de la aplicación.
     * @return La instancia de la base de datos.
     */
    @Provides
    @Singleton
    fun provideYourDatabase(
        context: Context
    ): BaseDatos {
        return synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                BaseDatos::class.java,
                "baseDatos"
            ).fallbackToDestructiveMigration().build()
            instance
        }
    }

    /**
     * Provee el DAO de usuario.
     *
     * @param db La instancia de la base de datos.
     * @return El DAO de usuario.
     */
    @Provides
    @Singleton
    fun provideYourDao(db: BaseDatos): UsuarioDao = db.userDao()

    /**
     * Provee el DAO de fecha.
     *
     * @param db La instancia de la base de datos.
     * @return El DAO de fecha.
     */
    @Provides
    @Singleton
    fun provideFechaDao(db: BaseDatos): FechaDao = db.fechaDao()

    /**
     * Provee el DAO de comida.
     *
     * @param db La instancia de la base de datos.
     * @return El DAO de comida.
     */
    @Provides
    @Singleton
    fun providesComidaDao(db: BaseDatos): ComidaDao = db.comidaDao()

    /**
     * Provee el DAO de rutina.
     *
     * @param db La instancia de la base de datos.
     * @return El DAO de rutina.
     */
    @Provides
    @Singleton
    fun providesRutinaDao(db: BaseDatos): RutinaDao = db.rutinaDao()

    /**
     * Provee el DAO de ejercicios.
     *
     * @param db La instancia de la base de datos.
     * @return El DAO de ejercicios.
     */
    @Provides
    @Singleton
    fun providesEjercicioDao(db: BaseDatos): EjercicioDao = db.EjercicioDao()
}