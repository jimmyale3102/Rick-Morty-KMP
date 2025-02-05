package com.alejo.rickmortyapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alejo.rickmortyapp.domain.model.CharacterModel
import com.alejo.rickmortyapp.ui.home.tabs.characters.CharacterOfTheDay

@Preview
@Composable
fun ComponentPreview() {
    CharacterOfTheDay(
//        CharacterModel(
//            id = "1",
//            isAlive = true,
//            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
//            name = "Rick",
//            species = "Species"
//        )
    )
}
