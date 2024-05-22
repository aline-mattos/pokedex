package com.line.pokedex.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.line.pokedex.model.PokemonDetails
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

@ProvidedTypeConverter
class TypeResponseConverter @Inject constructor(private val moshi: Moshi) {

  @TypeConverter
  fun fromString(value: String): List<PokemonDetails.TypeResponse>? {
    val adapter: JsonAdapter<List<PokemonDetails.TypeResponse>> = moshi.adapter(Types.newParameterizedType(List::class.java, PokemonDetails.TypeResponse::class.java))
    return adapter.fromJson(value)
  }

  @TypeConverter
  fun fromInfoType(type: List<PokemonDetails.TypeResponse>?): String {
    val adapter: JsonAdapter<List<PokemonDetails.TypeResponse>> = moshi.adapter(Types.newParameterizedType(List::class.java, PokemonDetails.TypeResponse::class.java))
    return adapter.toJson(type)
  }
}
