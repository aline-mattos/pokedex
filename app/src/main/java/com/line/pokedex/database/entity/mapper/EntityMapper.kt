package com.line.pokedex.database.entity.mapper

interface EntityMapper<Domain, Entity> {
  fun asEntity(domain: Domain): Entity

  fun asDomain(entity: Entity): Domain
}
