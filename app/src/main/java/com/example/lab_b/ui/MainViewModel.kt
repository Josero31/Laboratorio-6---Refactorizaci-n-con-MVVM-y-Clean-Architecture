package com.example.lab_b.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_b.data.remote.Pokemon
import com.example.lab_b.data.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: List<Pokemon>) : UiState()
    data class Error(val message: String) : UiState()
}

class MainViewModel(private val repository: MainRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        fetchPokemonList()
    }

    fun fetchPokemonList() {
        viewModelScope.launch {
            Log.d("MainViewModel", "Iniciando carga de pokémon...")
            _uiState.value = UiState.Loading

            val result = repository.getPokemonList()
            _uiState.value = result.fold(
                onSuccess = { response ->
                    Log.d("MainViewModel", "Éxito: ${response.results.size} pokémon cargados")
                    UiState.Success(response.results)
                },
                onFailure = { exception ->
                    val errorMessage = "Error al cargar los pokémon: ${exception.message}"
                    Log.e("MainViewModel", errorMessage, exception)
                    UiState.Error(errorMessage)
                }
            )
        }
    }
}
