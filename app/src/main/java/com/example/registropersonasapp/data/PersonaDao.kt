package com.example.registropersonasapp.data

import androidx.room.*
import com.example.registropersonasapp.model.Persona
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insertar(persona: Persona)

    @Delete
    suspend fun Eliminar(persona: Persona)

    @Query("SELECT * FROM personas ORDER BY personaId")
    fun GetLista(): Flow<List<Persona>>
}