package com.line.pokedex.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pokedex(
  @field:Json(name = "count") val count: Int,
  @field:Json(name = "next") val next: String?,
  @field:Json(name = "previous") val previous: String?,
  @field:Json(name = "results") val results: List<Pokemon>,
)
