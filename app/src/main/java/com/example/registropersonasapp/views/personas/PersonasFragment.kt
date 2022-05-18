package com.example.registropersonasapp.views.personas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.registropersonasapp.data.OcupacionDao
import com.example.registropersonasapp.data.PersonasDB
import com.example.registropersonasapp.databinding.PersonasFragmentBinding
import com.example.registropersonasapp.model.Ocupacion
import com.example.registropersonasapp.model.Persona
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class PersonasFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private val viewModel: PersonasViewModel by viewModels()
    private val viewModelOcupaciones: OcupacionesViewModel by viewModels()

    private lateinit var binding: PersonasFragmentBinding

    //atrapando argumentos
    private val args : PersonasFragmentArgs by navArgs()

    private var personaId: Int = 0
    private var ocupacionId : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PersonasFragmentBinding.inflate(inflater, container, false)

        LlenarCampos()

        val aaOcupaciones = ArrayAdapter<String>(inflater.context,
            android.R.layout.simple_spinner_dropdown_item)

        binding.ocupacionSpinner.onItemSelectedListener = this


        lifecycleScope.launch {
            viewModelOcupaciones.ocupaciones.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).
            collect {lista ->
                lista.forEach {
                    aaOcupaciones.add(it.nombre)
                }
            }
        }

        binding.ocupacionSpinner.adapter = aaOcupaciones

        binding.agregarOcupacionButton.setOnClickListener {
            openOcupacionesFragment()
        }

        binding.guardarButton.setOnClickListener {
            viewModel.guardar(
                Persona(
                    personaId,
                    binding.nombresEditText.text.toString(),
                    binding.emailEditText.text.toString(),
                    ocupacionId,
                    binding.salarioEditText.floatValue()
                )
            )
        }

        if(args.persona == null){
            binding.eliminarButton.visibility = View.GONE
        }

        binding.eliminarButton.setOnClickListener {
            args.persona?.let { it1 -> viewModel.eliminar(it1) }
            Snackbar.make(binding.salarioEditText, "Eliminado", Snackbar.LENGTH_LONG).show()
            findNavController().navigateUp()
        }


        viewModel.guardado.observe(viewLifecycleOwner){
            if (it) {
                Snackbar.make(binding.salarioEditText, "Guardo", Snackbar.LENGTH_LONG).show()
                findNavController().navigateUp()
            }
        }

        return binding.root
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        ocupacionId = position+1
        args.persona?.let { binding.ocupacionSpinner.setSelection(it.ocupacionId-1) }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    fun LlenarCampos(){
        val persona: Persona? = args.persona

        persona?.let {
            personaId = it.personaId
            binding.nombresEditText.setText(it.nombres)
            binding.emailEditText.setText(it.email)
            binding.salarioEditText.setText(it.salario.toString())
        }
    }

    fun TextInputEditText.floatValue() = text.toString().toFloatOrNull() ?: 0.0f

    fun openOcupacionesFragment(ocupacion: Ocupacion?=null)  {
        val action = PersonasFragmentDirections.actionToOcupacionesFragment(ocupacion)
        findNavController().navigate(action)
    }

}