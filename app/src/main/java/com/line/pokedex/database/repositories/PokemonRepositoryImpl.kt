package com.line.pokedex.database.repositories

import androidx.annotation.WorkerThread
import com.line.pokedex.database.PokemonDao
import com.line.pokedex.database.entity.mapper.asDomain
import com.line.pokedex.database.entity.mapper.asEntity
import com.line.pokedex.utils.Dispatcher
import com.line.pokedex.network.NetworkClient
import com.line.pokedex.utils.PokedexAppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
  private val networkClient: NetworkClient,
  private val pokemonDao: PokemonDao,
  @Dispatcher(PokedexAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher) : PokemonRepository {

  @WorkerThread
  override fun fetchPokemons(page: Int, onStart: () -> Unit, onComplete: () -> Unit, onError: (String?) -> Unit) = flow {
    var pokemons = pokemonDao.getPokemonList(page).asDomain()

    if (pokemons.isEmpty()) {
      val response = networkClient.fetchPokemons(page = page)

      if (response.isSuccessful) {
        response.body()?.let {
          pokemons = it.results
          pokemons.forEach { pokemon -> pokemon.page = page }
          pokemonDao.insertPokemonList(pokemons.asEntity())
          emit(pokemonDao.getAllPokemonList(page).asDomain())
        }
      } else {
        onError(response.message())
      }
    } else {
      emit(pokemonDao.getAllPokemonList(page).asDomain())
    }
  }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
