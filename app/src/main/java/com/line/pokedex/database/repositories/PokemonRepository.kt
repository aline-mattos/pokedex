package com.line.pokedex.database.repositories

import androidx.annotation.WorkerThread
import com.line.pokedex.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    @WorkerThread
    fun fetchPokemons(page: Int, onStart: () -> Unit, onComplete: () -> Unit, onError: (String?) -> Unit): Flow<List<Pokemon>>
}