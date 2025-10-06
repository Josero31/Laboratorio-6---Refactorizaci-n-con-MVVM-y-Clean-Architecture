package com.example.lab_b.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_b.data.remote.PokemonDetail
import com.example.lab_b.data.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val pokemon: PokemonDetail) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}

class DetailViewModel(private val repository: MainRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun fetchPokemonDetail(pokemonId: Int) {
        Log.d("DetailViewModel", "Fetching pokemon detail for ID: $pokemonId")
        viewModelScope.launch {
            _uiState.value = DetailUiState.Loading

            repository.getPokemonDetail(pokemonId).fold(
                onSuccess = { pokemonDetail ->
                    Log.d("DetailViewModel", "Pokemon detail loaded successfully: ${pokemonDetail.name}")
                    _uiState.value = DetailUiState.Success(pokemonDetail)
                },
                onFailure = { exception ->
                    Log.e("DetailViewModel", "Error loading pokemon detail", exception)
                    _uiState.value = DetailUiState.Error(
                        exception.message ?: "Error desconocido al cargar los detalles del pokémon"
                    )
                }
            )
        }
    }
}
