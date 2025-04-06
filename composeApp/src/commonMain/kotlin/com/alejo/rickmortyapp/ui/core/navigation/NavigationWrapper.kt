package com.alejo.rickmortyapp.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.alejo.rickmortyapp.domain.model.CharacterModel
import com.alejo.rickmortyapp.ui.character.CharacterDetailsScreen
import com.alejo.rickmortyapp.ui.home.HomeScreen
import kotlinx.serialization.json.Json

@Composable
fun NavigationWrapper() {
    val mainNavController = rememberNavController()

    NavHost(navController = mainNavController, startDestination = Routes.Home.route) {
        // Define your navigation graph here
        composable(Routes.Home.route) {
            HomeScreen(mainNavController)
        }
        composable<CharacterDetails> { backStackEntry ->
            val characterEncoded = backStackEntry.toRoute<CharacterDetails>()
            val character = Json.decodeFromString<CharacterModel>(characterEncoded.characterModel)
            CharacterDetailsScreen(character)
        }
    }
}

