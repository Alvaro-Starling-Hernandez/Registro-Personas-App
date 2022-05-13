package com.example.registropersonasapp.views.personas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registropersonasapp.data.PersonaDao
import com.example.registropersonasapp.model.Persona
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonasViewModel @Inject constructor(
    val personaDao : PersonaDao
) : ViewModel() {

    val personas : Flow<List<Persona>>
        get() =  personaDao.GetLista()

    private val _guardado = MutableLiveData(false)
    val guardado: LiveData<Boolean> get() = _guardado

    fun guardar(persona: Persona){
        viewModelScope.launch {
            personaDao.Insertar(persona)
            _guardado.value=true
        }
    }
}