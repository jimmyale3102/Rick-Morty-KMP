package com.alejo.rickmortyapp.ui.core.navigation.bottomNavigation

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.alejo.rickmortyapp.ui.core.navigation.Routes
import org.jetbrains.compose.resources.painterResource
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.ic_characters
import rickmortyapp.composeapp.generated.resources.ic_episodes

sealed class BottomBarItem {
    abstract val route: String
    abstract val title: String
    abstract val icon: @Composable () -> Unit

    data class Episodes(
        override val route: String = Routes.Episodes.route,
        override val title: String = "Episodes",
        override val icon: @Composable () -> Unit = {
            Icon(painter = painterResource(Res.drawable.ic_episodes), contentDescription = title)
        }
    ) : BottomBarItem()

    data class Characters(
        override val route: String = Routes.Characters.route,
        override val title: String = "Characters",
        override val icon: @Composable () -> Unit = {
            Icon(painter = painterResource(Res.drawable.ic_characters), contentDescription = title)
        }
    ) : BottomBarItem()
}