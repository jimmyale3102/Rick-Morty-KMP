package com.alejo.rickmortyapp.ui.core.navigation.bottomNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alejo.rickmortyapp.ui.core.navigation.Routes
import com.alejo.rickmortyapp.ui.home.tabs.characters.CharactersScreen
import com.alejo.rickmortyapp.ui.home.tabs.episodes.EpisodesScreen

@Composable
fun BottomNavigationWrapper(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Episodes.route) {
        composable(Routes.Episodes.route) {
            EpisodesScreen()
        }
        composable(Routes.Characters.route) {
            CharactersScreen()
        }
    }
}