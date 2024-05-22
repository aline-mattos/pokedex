package com.line.pokedex.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.line.pokedex.databinding.VhPokemonBinding
import com.line.pokedex.model.Pokemon

class PokemonViewHolder(private val binding: VhPokemonBinding, onClick: (Pokemon) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            binding.pokemon?.let {
                onClick(it)
            }
        }
    }

    fun bind(pokemon: Pokemon) {
        binding.pokemon = pokemon
        binding.executePendingBindings()
    }
}