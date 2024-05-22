package com.line.pokedex.network

import com.line.pokedex.model.Pokedex
import com.line.pokedex.model.PokemonDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexService {

  @GET("pokemon")
  fun fetchPokemons(@Query("limit") limit: Int = 20, @Query("offset") offset: Int = 0): Call<Pokedex>

  @GET("pokemon/{name}")
  fun fetchDetails(@Path("name") name: String): Call<PokemonDetails>
}
