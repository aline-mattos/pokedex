package com.line.pokedex.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.line.pokedex.model.PokemonDetails

@Entity
data class PokemonDetailsEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
    val types: List<PokemonDetails.TypeResponse>,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val speed: Int,
    val exp: Int,
)
