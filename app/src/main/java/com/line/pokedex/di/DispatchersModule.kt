package com.line.pokedex.di

import com.line.pokedex.utils.Dispatcher
import com.line.pokedex.utils.PokedexAppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
internal object DispatchersModule {

  @Provides
  @Dispatcher(PokedexAppDispatchers.IO)
  fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}
