package com.alejo.rickmortyapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alejo.rickmortyapp.ui.core.BackgroundPrimaryColor
import com.alejo.rickmortyapp.ui.core.BackgroundSecondaryColor
import com.alejo.rickmortyapp.ui.core.BackgroundTertiaryColor
import com.alejo.rickmortyapp.ui.core.DefaultTextColor
import com.alejo.rickmortyapp.ui.core.Green
import com.alejo.rickmortyapp.ui.core.navigation.bottomNavigation.BottomBarItem
import com.alejo.rickmortyapp.ui.core.navigation.bottomNavigation.BottomBarItem.Characters
import com.alejo.rickmortyapp.ui.core.navigation.bottomNavigation.BottomBarItem.Episodes
import com.alejo.rickmortyapp.ui.core.navigation.bottomNavigation.BottomNavigationWrapper
import org.jetbrains.compose.resources.painterResource
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.app_logo

@Composable
fun HomeScreen(mainNavController: NavHostController) {
    val items = listOf(Episodes(), Characters())
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar(items, navController) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            BottomNavigationWrapper(navController, mainNavController)
        }
    }
}

@Composable
fun TopBar() {
    Box(Modifier.fillMaxWidth().background(BackgroundPrimaryColor), contentAlignment = Alignment.TopCenter) {
        Image(
            painter = painterResource(Res.drawable.app_logo),
            contentDescription = null,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun BottomBar(items: List<BottomBarItem>, navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomAppBar(
        containerColor = BackgroundSecondaryColor,
        contentColor = Green
    ) {
        items.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Green,
                    selectedIconColor = BackgroundTertiaryColor,
                    unselectedIconColor = Green
                ),
                icon = item.icon,
                label = { Text(item.title, color = DefaultTextColor) },
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