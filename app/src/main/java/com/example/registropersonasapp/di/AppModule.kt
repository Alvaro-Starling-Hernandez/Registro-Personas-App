package com.example.registropersonasapp.di

import android.content.Context
import androidx.room.Room
import com.example.registropersonasapp.data.OcupacionDao
import com.example.registropersonasapp.data.PersonaDao
import com.example.registropersonasapp.data.PersonasDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun ProvidePersonaDb(@ApplicationContext context: Context): PersonasDB {
        val DATABASE_NAME = "Personas.db"
        return Room.databaseBuilder(
            context,
            PersonasDB::class.java,
            DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun ProvidePersonaDAO(personasDB: PersonasDB): PersonaDao {
        return personasDB.personaDao
    }

    @Provides
    fun ProvideOcupacionDAO(personasDB: PersonasDB): OcupacionDao {
        return personasDB.ocupacionDao
    }
}