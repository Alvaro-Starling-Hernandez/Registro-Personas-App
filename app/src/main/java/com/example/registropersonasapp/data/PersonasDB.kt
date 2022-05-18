package com.example.registropersonasapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.registropersonasapp.model.Ocupacion
import com.example.registropersonasapp.model.Persona

@Database(
    entities = [Persona::class,Ocupacion::class],
    version = 2
)
abstract class PersonasDB : RoomDatabase(){
    abstract val personaDao: PersonaDao
    abstract val ocupacionDao: OcupacionDao
}