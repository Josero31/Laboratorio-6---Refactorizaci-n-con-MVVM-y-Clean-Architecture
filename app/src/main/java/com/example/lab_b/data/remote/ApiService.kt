package com.example.lab_b.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon?limit=100")
    suspend fun getPokemonList(): PokemonResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: Int): PokemonDetail
}

// Puedes ajustar el endpoint y el modelo según la API que uses.
