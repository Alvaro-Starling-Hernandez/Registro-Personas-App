package com.example.registropersonasapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.registropersonasapp.model.Persona

@Database(
    entities = [Persona::class],
    version = 1
)
abstract class PersonasDB : RoomDatabase(){
    abstract val personaDao: PersonaDao
}