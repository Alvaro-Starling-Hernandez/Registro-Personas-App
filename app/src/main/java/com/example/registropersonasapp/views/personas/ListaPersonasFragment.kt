package com.example.registropersonasapp.views.personas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.registropersonasapp.databinding.ListaPersonasFragmentBinding
import com.example.registropersonasapp.model.Persona
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListaPersonasFragment : Fragment() {

    private val viewModel: PersonasViewModel by viewModels()
    private lateinit var binding: ListaPersonasFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListaPersonasFragmentBinding.inflate(inflater, container ,false)

        val adapter = PersonasAdapter(::openPersonasFragment)

        binding.personasRecyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.personas.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).
            collect {lista ->
                adapter.submitList(lista)
            }
        }

        binding.personasButton.setOnClickListener {
           openPersonasFragment()
        }

        return binding.root
    }

    fun openPersonasFragment(persona: Persona?=null)  {
        val action = ListaPersonasFragmentDirections.actionToPersonasFragment(persona)
        findNavController().navigate(action)
    }

}