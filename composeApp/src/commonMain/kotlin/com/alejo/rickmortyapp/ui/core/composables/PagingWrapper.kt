package com.alejo.rickmortyapp.ui.core.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import com.alejo.rickmortyapp.ui.core.composables.PagingType.*
import com.alejo.rickmortyapp.ui.home.tabs.characters.CharacterOfTheDay

enum class PagingType {
    ROW, VERTICAL_GRID
}

@Composable
fun <T : Any> PagingWrapper(
    modifier: Modifier = Modifier,
    initView: @Composable () -> Unit = {},
    emptyView: @Composable () -> Unit = {},
    loadingItemsView: @Composable () -> Unit = {},
    pagingType: PagingType,
    pagingItems: LazyPagingItems<T>,
    contentHeader: @Composable () -> Unit = {},
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
            when (pagingType) {
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
                    LazyVerticalGrid(
                        modifier = modifier,
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item(span = { GridItemSpan(2) }) {
                            contentHeader()
                        }
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