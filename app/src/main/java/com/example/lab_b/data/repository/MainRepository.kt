package com.example.lab_b.data.repository

import android.util.Log
import com.example.lab_b.data.remote.ApiService
import com.example.lab_b.data.remote.PokemonResponse

class MainRepository(private val apiService: ApiService) {
    suspend fun getPokemonList(): Result<PokemonResponse> {
        return try {
            Log.d("MainRepository", "Iniciando llamada a la API...")
            val response = apiService.getPokemonList()
            Log.d("MainRepository", "Respuesta recibida: ${response.results.size} pokémon")
            Result.success(response)
        } catch (e: Exception) {
            Log.e("MainRepository", "Error en la llamada a la API: ${e.message}", e)
            Result.failure(e)
        }
    }
}
