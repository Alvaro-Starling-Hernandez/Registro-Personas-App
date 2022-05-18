package com.example.registropersonasapp.views.personas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registropersonasapp.data.OcupacionDao
import com.example.registropersonasapp.data.PersonaDao
import com.example.registropersonasapp.model.Ocupacion
import com.example.registropersonasapp.model.Persona
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OcupacionesViewModel @Inject constructor(
    val ocupacionDao : OcupacionDao
): ViewModel() {

    val ocupaciones : Flow<List<Ocupacion>>
        get() =  ocupacionDao.GetLista()

    private val _guardado = MutableLiveData(false)
    val guardado: LiveData<Boolean> get() = _guardado

    fun guardar(ocupacion: Ocupacion){
        viewModelScope.launch {
            ocupacionDao.Insertar(ocupacion)
            _guardado.value=true
        }
    }

    fun eliminar(ocupacion: Ocupacion){
        viewModelScope.launch {
            ocupacionDao.Eliminar(ocupacion)
        }
    }
}