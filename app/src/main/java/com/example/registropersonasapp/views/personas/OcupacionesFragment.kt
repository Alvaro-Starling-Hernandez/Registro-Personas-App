package com.example.registropersonasapp.views.personas

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.registropersonasapp.R

class OcupacionesFragment : Fragment() {

    companion object {
        fun newInstance() = OcupacionesFragment()
    }

    private lateinit var viewModel: OcupacionesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ocupaciones_fragment, container, false)
    }


}