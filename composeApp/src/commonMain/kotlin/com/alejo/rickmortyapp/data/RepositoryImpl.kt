package com.alejo.rickmortyapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alejo.rickmortyapp.data.remote.ApiService
import com.alejo.rickmortyapp.data.remote.paging.CharactersPagingSource
import com.alejo.rickmortyapp.domain.Repository
import com.alejo.rickmortyapp.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val api: ApiService,
    private val charactersPagingSource: CharactersPagingSource
) : Repository {

    companion object {
        const val MAX_ITEMS = 20
        const val PREFETCH_ITEMS = 5
    }

    override suspend fun getCharacterById(id: String): CharacterModel {
        return api.getCharacterById(id).toDomain()
    }

    override fun getAllCharacters(page: Int): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = MAX_ITEMS,
                prefetchDistance = PREFETCH_ITEMS
            ),
            pagingSourceFactory = { charactersPagingSource }
        ).flow
    }
}