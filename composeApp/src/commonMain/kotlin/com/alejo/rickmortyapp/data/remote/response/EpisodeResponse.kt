package com.alejo.rickmortyapp.data.remote.response

import com.alejo.rickmortyapp.domain.model.EpisodeModel
import com.alejo.rickmortyapp.domain.model.SeasonEpisode
import com.alejo.rickmortyapp.domain.model.SeasonEpisode.*
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponse(
    val id: Int,
    val name: String,
    val episode: String,
    val characters: List<String>
) {
    fun toDomain(): EpisodeModel {
        val season = getSeasonFromEpisodeCode(episode)
        return EpisodeModel(
            id = id,
            name = name,
            episode = episode,
            characters = characters.map { it.substringAfterLast("/") },
            season = season,
            videoURL = getVideoURLFromSeason(season)
        )
    }

    private fun getVideoURLFromSeason(season: SeasonEpisode): String {
        return when (season) {
            SEASON_1 -> "https://phggkrploytfwiajdkot.supabase.co/storage/v1/object/public/RickMortyVideo//se1.mp4"
            SEASON_2 -> "https://phggkrploytfwiajdkot.supabase.co/storage/v1/object/public/RickMortyVideo//se1.mp4"
            SEASON_3 -> "https://phggkrploytfwiajdkot.supabase.co/storage/v1/object/public/RickMortyVideo//se1.mp4"
            SEASON_4 -> "https://phggkrploytfwiajdkot.supabase.co/storage/v1/object/public/RickMortyVideo//se1.mp4"
            SEASON_5 -> "https://phggkrploytfwiajdkot.supabase.co/storage/v1/object/public/RickMortyVideo//se1.mp4"
            SEASON_6 -> "https://phggkrploytfwiajdkot.supabase.co/storage/v1/object/public/RickMortyVideo//se1.mp4"
            SEASON_7 -> "https://phggkrploytfwiajdkot.supabase.co/storage/v1/object/public/RickMortyVideo//se1.mp4"
            else -> "https://phggkrploytfwiajdkot.supabase.co/storage/v1/object/public/RickMortyVideo//se1.mp4"
        }
    }

    private fun getSeasonFromEpisodeCode(episode: String): SeasonEpisode {
        return when {
            episode.startsWith("S01") -> SEASON_1
            episode.startsWith("S02") -> SEASON_2
            episode.startsWith("S03") -> SEASON_3
            episode.startsWith("S04") -> SEASON_4
            episode.startsWith("S05") -> SEASON_5
            episode.startsWith("S06") -> SEASON_6
            episode.startsWith("S07") -> SEASON_7
            else -> UNKNOWN
        }
    }
}