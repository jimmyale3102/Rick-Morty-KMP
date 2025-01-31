package com.alejo.rickmortyapp.ui.character

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alejo.rickmortyapp.domain.model.CharacterModel

@Composable
fun CharacterDetailsScreen(character: CharacterModel) {
    Box(Modifier.fillMaxSize()) {
        Text(text = character.name)
    }
}