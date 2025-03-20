package com.devj.todoproducts.core.presenter.composables


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devj.todoproducts.core.domain.model.Product
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfiniteGrid(
    modifier: Modifier = Modifier,
    gridKey: Any? = null,
    columns: GridCells,
    elements: List<Product>,
    itemBuilder: @Composable (value: Product) -> Unit,
    gridState: LazyGridState = rememberLazyGridState(),
    buffer: Int = 2,
    onLoadMore: suspend () -> Unit,
    onRefresh: () -> Unit,
    paginationState: PaginationState,
    initialLoadingBuilder: @Composable () -> Unit = { CircularProgressIndicator() },
    errorBuilder: @Composable () -> Unit = { Text(text = "Error") },
    loadingNextBuilder: @Composable () -> Unit = { CircularProgressIndicator() },
    emptyBuilder: @Composable () -> Unit = {},
    endOfListBuilder: @Composable () -> Unit = {},
) {
    val scope = rememberCoroutineScope()
    val refreshState = rememberPullToRefreshState()

    val reachedBottom: Boolean by remember {
        derivedStateOf { gridState.reachedBottom(buffer = buffer) }
    }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom) scope.launch {
            onLoadMore()
        }
    }
    if (elements.isEmpty() && paginationState.isIdle) {
        emptyBuilder()
    }
    if (paginationState.isInitialLoading) {
        initialLoadingBuilder()
    }

    PullToRefreshBox(
        isRefreshing = paginationState.isInitialLoading,
        onRefresh = onRefresh,
        modifier = modifier,
        state = refreshState,
    ) {
        LazyVerticalGrid(
            columns = columns,
            state = gridState,
            modifier = Modifier,
        ) {
            items(elements, key = { "$gridKey-${it.id}-${LocalDateTime.now()}" }) {
                itemBuilder(it)
            }
            item {
                when (paginationState) {
                    PaginationState.IDLE -> {}
                    PaginationState.LOADING -> initialLoadingBuilder()
                    PaginationState.PAGINATING -> loadingNextBuilder()
                    PaginationState.ERROR -> errorBuilder()
                    PaginationState.PAGINATION_EXHAUSTED -> endOfListBuilder()
                }
            }
            item {
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }


}

enum class PaginationState {
    IDLE, LOADING, PAGINATING, ERROR, PAGINATION_EXHAUSTED;

    val isInitialLoading: Boolean get() = this == LOADING
    val loadNextPage: Boolean get() = this == PAGINATING
    val isError: Boolean get() = this == ERROR
    val isIdle: Boolean get() = this == IDLE

}

internal fun LazyListState.reachedBottom(buffer: Int = 1): Boolean {
    val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
    return lastVisibleItem?.index == (layoutInfo.totalItemsCount - 1) - buffer
}

internal fun LazyGridState.reachedBottom(buffer: Int = 1): Boolean {
    val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: return false
    val totalItems = layoutInfo.totalItemsCount

    if (totalItems == 0) return false

    return lastVisibleItemIndex >= totalItems - buffer - 1
}