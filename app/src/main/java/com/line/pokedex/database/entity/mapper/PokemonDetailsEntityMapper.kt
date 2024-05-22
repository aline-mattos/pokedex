package com.line.pokedex.database.entity.mapper

import com.line.pokedex.database.entity.PokemonDetailsEntity
import com.line.pokedex.model.PokemonDetails

object PokemonInfoEntityMapper : EntityMapper<PokemonDetails, PokemonDetailsEntity> {

  override fun asEntity(domain: PokemonDetails): PokemonDetailsEntity {
    return PokemonDetailsEntity(
      id = domain.id,
      name = domain.name,
      height = domain.height,
      weight = domain.weight,
      experience = domain.experience,
      types = domain.types,
      hp = domain.hp,
      attack = domain.attack,
      defense = domain.defense,
      speed = domain.speed,
      exp = domain.exp,
    )
  }

  override fun asDomain(entity: PokemonDetailsEntity): PokemonDetails {
    return PokemonDetails(
      id = entity.id,
      name = entity.name,
      height = entity.height,
      weight = entity.weight,
      experience = entity.experience,
      types = entity.types,
      hp = entity.hp,
      attack = entity.attack,
      defense = entity.defense,
      speed = entity.speed,
      exp = entity.exp,
    )
  }
}

fun PokemonDetails.asEntity(): PokemonDetailsEntity {
  return PokemonInfoEntityMapper.asEntity(this)
}

fun PokemonDetailsEntity.asDomain(): PokemonDetails {
  return PokemonInfoEntityMapper.asDomain(this)
}
