package com.alejo.rickmortyapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alejo.rickmortyapp.ui.core.navigation.bottomNavigation.BottomBarItem
import com.alejo.rickmortyapp.ui.core.navigation.bottomNavigation.BottomBarItem.Characters
import com.alejo.rickmortyapp.ui.core.navigation.bottomNavigation.BottomBarItem.Episodes
import com.alejo.rickmortyapp.ui.core.navigation.bottomNavigation.BottomNavigationWrapper

@Composable
fun HomeScreen(mainNavController: NavHostController) {
    val items = listOf(Episodes(), Characters())
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(items, navController) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            BottomNavigationWrapper(navController, mainNavController)
        }
    }
}

@Composable
fun BottomBar(items: List<BottomBarItem>, navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomAppBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = item.icon,
                label = { Text(item.title) },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(route = item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

    }
}