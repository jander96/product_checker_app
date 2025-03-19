package com.devj.todoproducts.features.unreviewed.presenter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devj.todoproducts.core.domain.model.Product
import com.devj.todoproducts.core.presenter.composables.PaginationState
import com.devj.todoproducts.core.presenter.model.AsyncState
import com.devj.todoproducts.features.unreviewed.domain.usecase.GetUnreviewedProducts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UnreviewedViewModel(private val getUnreviewedProducts: GetUnreviewedProducts) : ViewModel() {
    companion object {
        const val PAGE_SIZE = 7;
    }

    private val _viewState: MutableStateFlow<UnreviewedProductsState> =
        MutableStateFlow(UnreviewedProductsState())
    val viewState: StateFlow<UnreviewedProductsState> = _viewState.asStateFlow()
    private var page by mutableIntStateOf(1)

    fun getProducts() {
        _viewState.update {
            it.copy(
                asyncState = AsyncState.LOADING,
                paginationState = if (page == 1) PaginationState.LOADING else PaginationState.PAGINATING
            )
        }
        viewModelScope.launch {
            try {
                val pagination = getUnreviewedProducts(page, PAGE_SIZE)
                _viewState.update {
                    it.copy(
                        asyncState = AsyncState.SUCCESS,
                        products = it.products + pagination.products,
                        paginationState = pagination.products.run {
                            if (size < PAGE_SIZE) PaginationState.PAGINATION_EXHAUSTED
                            else PaginationState.IDLE
                        },
                    )
                }
                page++
            } catch (e: Exception) {
                _viewState.update {
                    it.copy(
                        asyncState = AsyncState.ERROR,
                        error = e,
                        paginationState = PaginationState.ERROR
                    )
                }
            }
        }
    }
}

data class UnreviewedProductsState(
    val asyncState: AsyncState = AsyncState.INITIAL,
    val products: List<Product> = emptyList(),
    val paginationState: PaginationState = PaginationState.IDLE,
    val error: Throwable? = null,
)
