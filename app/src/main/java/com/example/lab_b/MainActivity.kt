package com.example.lab_b

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab_b.data.remote.RetrofitClient
import com.example.lab_b.data.repository.MainRepository
import com.example.lab_b.ui.theme.Lab_bTheme
import com.example.lab_b.ui.MainScreen
import com.example.lab_b.ui.DetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate llamado")
        setContent {
            Lab_bTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val repository = remember { MainRepository(RetrofitClient.apiService) }
                    PokemonApp(repository = repository)
                }
            }
        }
    }
}

@Composable
fun PokemonApp(repository: MainRepository) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(
                onPokemonClick = { pokemonId, pokemonName ->
                    navController.navigate("detail/$pokemonId/$pokemonName")
                },
                repository = repository
            )
        }

        composable("detail/{pokemonId}/{pokemonName}") { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getString("pokemonId")?.toIntOrNull() ?: 1
            val pokemonName = backStackEntry.arguments?.getString("pokemonName") ?: ""

            DetailScreen(
                pokemonId = pokemonId,
                pokemonName = pokemonName,
                onBackClick = {
                    navController.popBackStack()
                },
                repository = repository
            )
        }
    }
}
