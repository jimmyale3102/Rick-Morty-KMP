package com.alejo.rickmortyapp.ui.core.navigation

import kotlinx.serialization.Serializable

sealed class Routes(val route: String) {
    data object Home : Routes("home")

    // Home Bottom Bar
    data object Episodes : Routes("episodes")
    data object Characters : Routes("characters")
}

@Serializable
data class CharacterDetails(val characterModel: String)