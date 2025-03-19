package com.devj.todoproducts.features.unreviewed.presenter.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devj.todoproducts.core.domain.model.Product
import com.devj.todoproducts.core.presenter.composables.InfiniteGrid
import com.devj.todoproducts.core.presenter.composables.ProductCard
import com.devj.todoproducts.features.unreviewed.presenter.viewmodel.UnreviewedProductsState
import kotlinx.coroutines.launch

@Composable
fun UnreviewedProductsGrid(
    modifier: Modifier = Modifier,
    state: UnreviewedProductsState,
    gridState: LazyGridState = rememberLazyGridState(),
    onLoad: suspend () -> Unit = {},
    onItemClick: (Product) -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    InfiniteGrid(
        modifier = modifier,
        gridState = gridState,
        elements = state.products,
        paginationState = state.paginationState,
        columns = GridCells.Adaptive(200.dp),

        initialLoadingBuilder = {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        },
        errorBuilder = {
            Column (modifier = Modifier.fillMaxSize()) {
                Text(text = state.error?.message ?: "", modifier = Modifier)
                ElevatedButton(onClick = {
                    scope.launch {
                        onLoad()
                    }
                }) {}
            }
        },
        itemBuilder = { product ->
            ProductCard(
                modifier = Modifier,
                product = product,
                onItemClick = onItemClick,
                onCheckedChange = {

                }
            )
        },
        onLoadMore = onLoad
    )
}