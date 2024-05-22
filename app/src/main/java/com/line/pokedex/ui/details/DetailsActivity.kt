package com.line.pokedex.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.line.pokedex.R
import com.line.pokedex.databinding.ActivityDetailsBinding
import com.line.pokedex.model.Pokemon
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    companion object {
        internal const val POKEMON = "POKEMON"

        fun startActivity(context: Context, pokemon: Pokemon) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(POKEMON, pokemon)
            context.startActivity(intent)
        }
    }

    @Inject
    internal lateinit var detailViewModelFactory: DetailsViewModel.AssistedFactory

    lateinit var binding: ActivityDetailsBinding
    lateinit var pokemon: Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemon = intent.getParcelableExtra(POKEMON)!!

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        val viewModel: DetailsViewModel by viewModels {
            DetailsViewModel.provideFactory(detailViewModelFactory, pokemon.name)
        }

        binding.vm = viewModel

        lifecycleScope.launch {
            viewModel.flow.collect {
                binding.details = it
            }
        }

        binding.pokemon = pokemon
    }
}