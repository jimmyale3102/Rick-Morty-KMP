package com.alejo.rickmortyapp.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    @SerialName("id") val id: String,
    @SerialName("status") val status: String,
    @SerialName("image") val image: String
)