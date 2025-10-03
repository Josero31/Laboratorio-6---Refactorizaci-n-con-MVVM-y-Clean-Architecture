package com.example.lab_b.data.repository

import com.example.lab_b.data.remote.ApiService
import com.example.lab_b.data.remote.PokemonResponse

class MainRepository(private val apiService: ApiService) {
    suspend fun getPokemonList(): Result<PokemonResponse> {
        return try {
            val response = apiService.getPokemonList()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
