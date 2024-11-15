package com.alejo.rickmortyapp.ui.core.navigation

sealed class Routes(val route: String) {
    data object Home : Routes("home")

    // Home Bottom Bar
    data object Episodes : Routes("episodes")
    data object Characters : Routes("characters")
}