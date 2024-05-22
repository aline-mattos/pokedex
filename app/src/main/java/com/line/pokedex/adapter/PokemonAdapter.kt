package com.line.pokedex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.line.pokedex.databinding.VhPokemonBinding
import com.line.pokedex.model.Pokemon
import com.line.pokedex.adapter.viewholder.PokemonViewHolder

class PokemonAdapter(private var list: List<Pokemon>, private val onClick: (Pokemon) -> Unit): RecyclerView.Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
        PokemonViewHolder(VhPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false), onClick)

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    fun updateData(list: List<Pokemon>) {
        this.list = list
        notifyDataSetChanged()
    }
}
