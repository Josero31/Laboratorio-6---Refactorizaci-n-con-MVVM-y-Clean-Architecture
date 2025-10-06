# Laboratorio 6 - Refactorización con MVVM y Clean Architecture

## Descripción
Aplicación Android que consume la API de Pokémon (https://pokeapi.co/) para mostrar una lista de los primeros 100 Pokémon y permitir ver los detalles de cada uno con sus diferentes sprites.

## Arquitectura Implementada

### MVVM (Model-View-ViewModel)
- **Model**: Clases de datos (`Pokemon`, `PokemonDetail`, `PokemonSprites`)
- **View**: Composables (`MainScreen`, `DetailScreen`)  
- **ViewModel**: Lógica de presentación (`MainViewModel`, `DetailViewModel`)

### Clean Architecture
```
ui/
├── MainActivity.kt          # Actividad principal con navegación
├── MainScreen.kt           # Pantalla principal con lista de Pokémon
├── DetailScreen.kt         # Pantalla de detalles con sprites
├── MainViewModel.kt        # ViewModel para la lista
├── DetailViewModel.kt      # ViewModel para detalles
├── MainViewModelFactory.kt # Factory para MainViewModel
└── DetailViewModelFactory.kt # Factory para DetailViewModel

data/
├── repository/
│   └── MainRepository.kt   # Intermediario entre UI y datos
└── remote/
    ├── ApiService.kt       # Definición de endpoints
    ├── RetrofitClient.kt   # Configuración de Retrofit
    └── PokemonResponse.kt  # Modelos de datos
```

## Características Implementadas

### ✅ Requisitos Cumplidos
1. **ViewModel con StateFlow**: Manejo del estado reactivo
2. **Repository como intermediario**: Separación entre datos y presentación
3. **Capa de datos con Retrofit**: Comunicación HTTP con la API
4. **Manejo de errores**: Estados de error en la UI con opción de reintentar
5. **Estructura modular**: Organización clara de capas

### 📱 Funcionalidades
- **Pantalla Principal (MainFragment)**:
  - Lista de 100 Pokémon con imágenes
  - Scroll vertical fluido
  - Navegación al tocar cualquier Pokémon

- **Pantalla de Detalles (DetailFragment)**:
  - 4 sprites del Pokémon seleccionado:
    - Front (normal)
    - Back (normal)  
    - Front Shiny
    - Back Shiny
  - Botón de regreso
  - Estados de carga y error

## Tecnologías Utilizadas
- **Jetpack Compose**: UI declarativa
- **Navigation Compose**: Navegación entre pantallas
- **Retrofit**: Cliente HTTP
- **StateFlow**: Manejo de estado reactivo
- **Coil**: Carga de imágenes
- **Coroutines**: Programación asíncrona

## Mejoras de MVVM vs Versión Anterior
1. **Separación de responsabilidades**: La lógica de negocio está en el ViewModel
2. **Testabilidad**: Cada capa puede ser probada independientemente
3. **Mantenibilidad**: Código más organizado y fácil de modificar
4. **Escalabilidad**: Estructura preparada para crecimiento futuro
5. **Manejo de estado**: StateFlow proporciona actualizaciones reactivas

## Configuración del Proyecto
```kotlin
// Dependencias principales
implementation("androidx.navigation:navigation-compose:2.7.5")
implementation("io.coil-kt:coil-compose:2.5.0")
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")
```

## Estado del Proyecto
- ✅ Arquitectura MVVM implementada
- ✅ Clean Architecture aplicada
- ✅ Navegación funcional
- ✅ API de Pokémon integrada
- ✅ Estados de carga y error manejados
- ✅ UI responsive y moderna
