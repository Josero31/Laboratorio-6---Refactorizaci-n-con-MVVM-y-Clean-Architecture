package com.example.lab_b.data.remote

data class PokemonResponse(
    val results: List<Pokemon>
)

data class Pokemon(
    val name: String,
    val url: String
) {
    // Extraer el ID del Pokémon desde la URL
    fun getId(): Int {
        return url.split("/").dropLast(1).last().toInt()
    }
}

// Modelo para los detalles completos del Pokémon
data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: PokemonSprites,
    val types: List<PokemonType>
)

data class PokemonSprites(
    val front_default: String?,
    val back_default: String?,
    val front_shiny: String?,
    val back_shiny: String?
)

data class PokemonType(
    val slot: Int,
    val type: TypeInfo
)

data class TypeInfo(
    val name: String,
    val url: String
)
