package com.line.pokedex.di

import com.line.pokedex.database.repositories.DetailRepository
import com.line.pokedex.database.repositories.DetailRepositoryImpl
import com.line.pokedex.database.repositories.PokemonRepository
import com.line.pokedex.database.repositories.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

  @Binds
  fun bindsMainRepository(mainRepositoryImpl: PokemonRepositoryImpl): PokemonRepository

  @Binds
  fun bindsDetailRepository(detailRepositoryImpl: DetailRepositoryImpl): DetailRepository
}
