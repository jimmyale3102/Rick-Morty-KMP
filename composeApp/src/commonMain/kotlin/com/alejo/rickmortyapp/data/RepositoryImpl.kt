package com.alejo.rickmortyapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alejo.rickmortyapp.data.database.RickMortyDatabase
import com.alejo.rickmortyapp.data.remote.ApiService
import com.alejo.rickmortyapp.data.remote.paging.CharactersPagingSource
import com.alejo.rickmortyapp.data.remote.paging.EpisodesPagingSource
import com.alejo.rickmortyapp.domain.Repository
import com.alejo.rickmortyapp.domain.model.CharacterModel
import com.alejo.rickmortyapp.domain.model.CharacterOfTheDayModel
import com.alejo.rickmortyapp.domain.model.EpisodeModel
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val api: ApiService,
    private val charactersPagingSource: CharactersPagingSource,
    private val episodesPagingSource: EpisodesPagingSource,
    private val database: RickMortyDatabase
) : Repository {

    companion object {
        const val MAX_ITEMS = 20
        const val PREFETCH_ITEMS = 5
    }

    override suspend fun getCharacterById(id: String): CharacterModel {
        return api.getCharacterById(id).toDomain()
    }

    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = MAX_ITEMS,
                prefetchDistance = PREFETCH_ITEMS
            ),
            pagingSourceFactory = { charactersPagingSource }
        ).flow
    }

    override fun getAllEpisodes(): Flow<PagingData<EpisodeModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = MAX_ITEMS,
                prefetchDistance = PREFETCH_ITEMS
            ),
            pagingSourceFactory = { episodesPagingSource }
        ).flow
    }

    override suspend fun getCharacterOfTheDayDB(): CharacterOfTheDayModel? {
        return database.getPreferencesDAO().getCharacterOfTheDay()?.toDomain()
    }

    override suspend fun saveCharacterEntity(characterOfTheDayModel: CharacterOfTheDayModel) {
        database.getPreferencesDAO().saveCharacterOfTheDay(characterOfTheDayModel.toEntity())
    }

    override suspend fun getCharacterEpisodes(episodes: List<String>): List<EpisodeModel> {
        if (episodes.isEmpty()) return emptyList()

        if (episodes.size > 1) {
            return api.getCharacterEpisodes(episodes.joinToString(","))
                .map { episodeResponse ->  episodeResponse.toDomain() }
        } else {
            return listOf(api.getSingleCharacterEpisodes(episodes.first()).toDomain())
        }
    }
}