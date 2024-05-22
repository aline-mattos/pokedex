package com.line.pokedex.ui.main

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.line.pokedex.database.repositories.PokemonRepository
import com.line.pokedex.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val pokemonRepository: PokemonRepository): ViewModel() {

    var isLoading: Boolean = false

    private val index: MutableStateFlow<Int> = MutableStateFlow(0)

    private val _pokemons: StateFlow<List<Pokemon>> = index.flatMapLatest { page ->
        pokemonRepository.fetchPokemons(
            page = page,
            onStart = { isLoading = true },
            onComplete = { isLoading = false },
            onError = { Log.e("MainViewModel", "fetchData: $it") },
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    val pokemons: LiveData<List<Pokemon>> = _pokemons.asLiveData()

    fun fetchNext() {
        index.value++
    }
}