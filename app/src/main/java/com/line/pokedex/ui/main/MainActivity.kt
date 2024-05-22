package com.line.pokedex.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.line.pokedex.R
import com.line.pokedex.adapter.PokemonAdapter
import com.line.pokedex.databinding.ActivityMainBinding
import com.line.pokedex.model.Pokemon
import com.line.pokedex.ui.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModels<MainViewModel>().value

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewModel
        setupRecyclerview()
    }

    private fun setupRecyclerview() {
        adapter = PokemonAdapter(emptyList(), onClick = { pokemon ->
            DetailsActivity.startActivity(this, pokemon)
        })

        binding.adapter = adapter
        viewModel.pokemons.observe(this) { pokemons ->
            adapter.updateData(pokemons)
        }
    }

}