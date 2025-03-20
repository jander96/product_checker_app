package com.devj.todoproducts.core.presenter

import com.devj.todoproducts.core.domain.model.Product
import com.devj.todoproducts.core.presenter.composables.PaginationState
import com.devj.todoproducts.core.presenter.model.AsyncState

data class ProductsState(
    val currentProductUnderModification: Product? = null,
    val asyncState: AsyncState = AsyncState.INITIAL,
    val products: List<Product> = emptyList(),
    val paginationState: PaginationState = PaginationState.IDLE,
    val error: Throwable? = null,
)

sealed class ProductEvent {
    data class ProductDeleted(val product: Product) : ProductEvent()
    data class ProductUpdated(val product: Product) : ProductEvent()
}
