package com.alejo.rickmortyapp.data.remote.response

import com.alejo.rickmortyapp.domain.model.CharacterModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    @SerialName("id") val id: String,
    @SerialName("status") val status: String,
    @SerialName("image") val image: String
) {
    fun toDomain(): CharacterModel = CharacterModel(
        id = id,
        isAlive = status.lowercase() == "alive",
        image = image
    )
}