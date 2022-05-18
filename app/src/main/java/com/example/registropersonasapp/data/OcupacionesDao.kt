package com.example.registropersonasapp.data

import androidx.room.*
import com.example.registropersonasapp.model.Ocupacion
import kotlinx.coroutines.flow.Flow

@Dao
interface OcupacionesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insertar(ocupacion: Ocupacion)

    @Delete
    suspend fun Eliminar(ocupacion: Ocupacion)

    @Query("SELECT * FROM ocupaciones ORDER BY ocupacionId")
    fun GetLista(): Flow<List<Ocupacion>>
}