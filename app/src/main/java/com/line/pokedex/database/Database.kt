package com.line.pokedex.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.line.pokedex.database.entity.PokemonDetailsEntity
import com.line.pokedex.database.entity.PokemonEntity

@Database(
    entities = [PokemonEntity::class, PokemonDetailsEntity::class],
    version = 2,
    exportSchema = true,
)
@TypeConverters(value = [TypeResponseConverter::class])
abstract class Database : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonDetailsDao(): PokemonDetailsDao
}
