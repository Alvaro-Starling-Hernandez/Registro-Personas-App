package com.example.registropersonasapp.views.personas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.registropersonasapp.databinding.RowPersonaBinding
import com.example.registropersonasapp.model.Persona

class PersonasAdapter(
    private var onItemClicked: ((persona:Persona) -> Unit)
) : ListAdapter<Persona, PersonasAdapter.PersonaViewHolder>(PersonaDiffCallBack()){

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val binding =
            RowPersonaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonaViewHolder(binding)
    }

    inner class PersonaViewHolder(private val binding: RowPersonaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Persona) {
            binding.personaIdTextView.text = item.personaId.toString()
            binding.nombresTextView.text = item.nombres
            binding.salarioTextView.text = item.salario.toString();

            binding.root.setOnClickListener {
                onItemClicked(item)
            }
        }
    }
}

class PersonaDiffCallBack : DiffUtil.ItemCallback<Persona>() {
    override fun areItemsTheSame(oldItem: Persona, newItem: Persona): Boolean {
        return oldItem.personaId == newItem.personaId
    }

    override fun areContentsTheSame(oldItem: Persona, newItem: Persona): Boolean {
        return oldItem == newItem
    }
}