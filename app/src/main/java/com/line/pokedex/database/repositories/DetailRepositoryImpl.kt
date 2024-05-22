package com.line.pokedex.database.repositories

import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import com.line.pokedex.utils.Dispatcher
import com.line.pokedex.network.NetworkClient
import com.line.pokedex.utils.PokedexAppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import com.line.pokedex.database.PokemonDetailsDao
import com.line.pokedex.database.entity.mapper.asDomain
import com.line.pokedex.database.entity.mapper.asEntity
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

@VisibleForTesting
class DetailRepositoryImpl @Inject constructor(
  private val networkClient: NetworkClient,
  private val pokemonDetailsDao: PokemonDetailsDao,
  @Dispatcher(PokedexAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher) : DetailRepository {

  @WorkerThread
  override fun fetchDetails(name: String, onComplete: () -> Unit, onError: (String?) -> Unit) = flow {
      val pokemonInfo = pokemonDetailsDao.getPokemonDetails(name)

      if (pokemonInfo == null) {
        val response = networkClient.fetchPokemonInfo(name = name)

        if(response.isSuccessful){
          response.body()?.let {
            pokemonDetailsDao.insertPokemonDetails(it.asEntity())
            emit(it)
          }
        } else {
          onError(response.message())
        }
      } else {
        emit(pokemonInfo.asDomain())
      }
    }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
