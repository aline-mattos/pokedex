package com.line.pokedex.network

import com.line.pokedex.model.Pokedex
import com.line.pokedex.model.PokemonDetails
import retrofit2.Response
import retrofit2.awaitResponse
import javax.inject.Inject

class NetworkClient @Inject constructor(private val pokedexService: PokedexService) {
  companion object {
    private const val PAGING_SIZE = 20
  }

  suspend fun fetchPokemons(page: Int): Response<Pokedex> = pokedexService.fetchPokemons(limit = PAGING_SIZE, offset = page * PAGING_SIZE).awaitResponse()

  suspend fun fetchPokemonInfo(name: String): Response<PokemonDetails> = pokedexService.fetchDetails(name = name).awaitResponse()
}
