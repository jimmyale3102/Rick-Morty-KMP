package com.alejo.rickmortyapp.data.remote

import com.alejo.rickmortyapp.data.remote.response.CharacterResponse
import com.alejo.rickmortyapp.data.remote.response.CharactersWrapperResponse
import com.alejo.rickmortyapp.data.remote.response.EpisodeResponse
import com.alejo.rickmortyapp.data.remote.response.EpisodesWrapperResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiService(private val client: HttpClient) {

    suspend fun getCharacterById(id: String): CharacterResponse {
        return client.get("/api/character/$id").body()
    }

    suspend fun getAllCharacters(page: Int): CharactersWrapperResponse {
        return client.get("/api/character/") {
            parameter("page", page)
        }.body()
    }

    suspend fun getAllEpisodes(page: Int): EpisodesWrapperResponse {
        return client.get("/api/episode/") {
            parameter("page", page)
        }.body()
    }

    suspend fun getCharacterEpisodes(episodes: String): List<EpisodeResponse> {
        return client.get("/api/episode/$episodes").body()
    }

    suspend fun getSingleCharacterEpisodes(episodeId: String): EpisodeResponse {
        return client.get("/api/episode/$episodeId").body()
    }

}