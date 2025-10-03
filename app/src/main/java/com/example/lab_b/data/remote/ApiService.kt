package com.example.lab_b.data.remote

import retrofit2.http.GET

interface ApiService {
    @GET("pokemon?limit=20")
    suspend fun getPokemonList(): PokemonResponse
}

// Puedes ajustar el endpoint y el modelo según la API que uses.
