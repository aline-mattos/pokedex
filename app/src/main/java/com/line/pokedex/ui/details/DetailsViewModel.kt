package com.line.pokedex.ui.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.line.pokedex.database.repositories.DetailRepository
import com.line.pokedex.model.PokemonDetails
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow

class DetailsViewModel @AssistedInject constructor(detailRepository: DetailRepository, @Assisted private val pokemonName: String) : ViewModel() {
    companion object {
        fun provideFactory(assistedFactory: AssistedFactory, pokemonName: String): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(pokemonName) as T
            }
        }
    }

    val flow: Flow<PokemonDetails?> = detailRepository.fetchDetails(
        name = pokemonName,
        onComplete = {},
        onError = { Log.e("DetailsViewModel", "fetchDetails: $it") }
    )

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(pokemonName: String): DetailsViewModel
    }
}
