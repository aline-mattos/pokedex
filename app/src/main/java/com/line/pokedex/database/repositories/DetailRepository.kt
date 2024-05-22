package com.line.pokedex.database.repositories

import androidx.annotation.WorkerThread
import com.line.pokedex.model.PokemonDetails
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
  @WorkerThread
  fun fetchDetails(name: String, onComplete: () -> Unit, onError: (String?) -> Unit): Flow<PokemonDetails>
}
