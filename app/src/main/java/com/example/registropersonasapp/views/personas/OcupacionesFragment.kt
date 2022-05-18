package com.example.registropersonasapp.views.personas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.registropersonasapp.databinding.OcupacionesFragmentBinding
import com.example.registropersonasapp.model.Ocupacion
import com.google.android.material.snackbar.Snackbar

class OcupacionesFragment : Fragment() {

    private val viewModel: OcupacionesViewModel by viewModels()
    private lateinit var binding: OcupacionesFragmentBinding

    //atrapando argumentos
    private val args : OcupacionesFragmentArgs by navArgs()

    private var ocupacionId : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OcupacionesFragmentBinding.inflate(inflater, container, false)

        binding.guardarButton.setOnClickListener {
            viewModel.guardar(
                Ocupacion(
                    ocupacionId,
                    binding.nombreEditText.text.toString()
                )
            )
        }

        viewModel.guardado.observe(viewLifecycleOwner){
            if (it) {
                Snackbar.make(binding.nombreEditText, "Guardo", Snackbar.LENGTH_LONG).show()
                findNavController().navigateUp()
            }
        }

        return binding.root
    }


}