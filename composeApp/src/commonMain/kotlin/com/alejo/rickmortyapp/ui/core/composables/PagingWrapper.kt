package com.alejo.rickmortyapp.ui.core.composables

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import com.alejo.rickmortyapp.ui.core.composables.PagingType.*

enum class PagingType {
    ROW, VERTICAL_GRID
}

@Composable
fun <T : Any> PagingWrapper(
    initView: @Composable () -> Unit = {},
    emptyView: @Composable () -> Unit = {},
    loadingItemsView: @Composable () -> Unit = {},
    pagingType: PagingType,
    pagingItems: LazyPagingItems<T>,
    content: @Composable (T) -> Unit
) {
    when {
        pagingItems.loadState.refresh is LoadState.Loading && pagingItems.itemCount == 0 -> {
            initView()
        }

        pagingItems.loadState.refresh is LoadState.NotLoading && pagingItems.itemCount == 0 -> {
            emptyView()
        }

        else -> {
            when(pagingType) {
                ROW -> {
                    LazyRow {
                        items(pagingItems.itemCount) { pos ->
                            pagingItems[pos]?.let {
                                content(it)
                            }
                        }
                    }
                }
                VERTICAL_GRID -> {
                    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                        items(pagingItems.itemCount) { pos ->
                            pagingItems[pos]?.let {
                                content(it)
                            }
                        }
                    }
                }
            }

            if (pagingItems.loadState.append is LoadState.Loading) {
                loadingItemsView()
            }
        }
    }
}