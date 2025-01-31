package com.alejo.rickmortyapp.ui.core.navigation.bottomNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alejo.rickmortyapp.ui.core.navigation.CharacterDetails
import com.alejo.rickmortyapp.ui.core.navigation.Routes
import com.alejo.rickmortyapp.ui.home.tabs.characters.CharactersScreen
import com.alejo.rickmortyapp.ui.home.tabs.episodes.EpisodesScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun BottomNavigationWrapper(navController: NavHostController, mainNavController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Episodes.route) {
        composable(Routes.Episodes.route) {
            EpisodesScreen()
        }
        composable(Routes.Characters.route) {
            CharactersScreen(
                navigateToCharacterDetails = { character ->
                    val characterEncoded = Json.encodeToString(character)
                    mainNavController.navigate(CharacterDetails(characterEncoded))
                }
            )
        }
    }
}