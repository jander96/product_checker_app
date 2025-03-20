package com.devj.todoproducts.core.presenter.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.devj.todoproducts.core.presenter.ProductsState
import kotlinx.coroutines.launch

@Composable
fun ProductsGrid(
    modifier: Modifier = Modifier,
    gridKey: Any? = null,
    state: ProductsState,
    gridState: LazyGridState = rememberLazyGridState(),
    onLoad: suspend () -> Unit = {},
    onRefresh: () -> Unit = {},
    onItemClick: (Product) -> Unit = {},
    onCheckedChange: (Product) -> Unit = {},
    onDelete: (Product) -> Unit = {},
    enableEdition: Boolean = true
) {
    val scope = rememberCoroutineScope()
    InfiniteGrid(
        modifier = modifier,
        gridKey = gridKey,
        gridState = gridState,
        elements = state.products,
        paginationState = state.paginationState,
        columns = GridCells.Adaptive(350.dp),

        initialLoadingBuilder = {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        },
        errorBuilder = {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = state.error?.message ?: "", modifier = Modifier)
                ElevatedButton(onClick = {
                    scope.launch {
                        onLoad()
                    }
                }) {}
            }
        },
        itemBuilder = { product ->

            SwipeDelete(
                modifier = Modifier,
                product = product,
                onDelete = onDelete,
            ) {
                ProductCard(
                    enabled = enableEdition,
                    modifier = Modifier,
                    product = product,
                    onItemClick = onItemClick,
                    onCheckedChange = {
                        onCheckedChange(product.copy(approved = it, checked = true))
                    },
                    isUnderModification = product.id == state.currentProductUnderModification?.id
                )
            }
        },
        endOfListBuilder = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
            ) {
                Text("No more products to show", modifier = Modifier)
            }
        },
        onLoadMore = onLoad,
        onRefresh = onRefresh,
    )
}