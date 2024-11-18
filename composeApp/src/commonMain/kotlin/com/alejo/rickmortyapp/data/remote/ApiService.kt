package com.alejo.rickmortyapp.data.remote

import com.alejo.rickmortyapp.data.remote.response.CharacterResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(private val client: HttpClient) {

    suspend fun getCharacterById(id: String): CharacterResponse {
        return client.get("/api/character/$id").body()
    }

}