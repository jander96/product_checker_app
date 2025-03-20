package com.devj.todoproducts.features.unreviewed.presenter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devj.todoproducts.core.domain.data.ProductRepository
import com.devj.todoproducts.core.domain.model.Product
import com.devj.todoproducts.core.presenter.ProductEvent
import com.devj.todoproducts.core.presenter.ProductsState
import com.devj.todoproducts.core.presenter.composables.PaginationState
import com.devj.todoproducts.core.presenter.model.AsyncState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UnreviewedViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {
    companion object {
        const val PAGE_SIZE = 10
    }

    private val _viewState: MutableStateFlow<ProductsState> =
        MutableStateFlow(ProductsState())
    val viewState: StateFlow<ProductsState> = _viewState.asStateFlow()
    private var page by mutableIntStateOf(1)

    private val _events = MutableSharedFlow<ProductEvent?>()
    val events: SharedFlow<ProductEvent?> get() = _events.asSharedFlow()

    init {
        getProducts()
    }

    fun refresh() {
        page = 1
        _viewState.update { ProductsState() }
        getProducts()
    }

    fun getProducts() {
        _viewState.update {
            it.copy(
                asyncState = AsyncState.LOADING,
                paginationState = if (page == 1) PaginationState.LOADING else PaginationState.PAGINATING
            )
        }
        viewModelScope.launch {
            try {
                val incomingProducts =
                    productRepository.getProductsRemote(page, PAGE_SIZE).filter { !it.checked }
                _viewState.update {
                    it.copy(
                        asyncState = AsyncState.SUCCESS,
                        products = if (page == 1) incomingProducts else it.products + incomingProducts,
                        paginationState = incomingProducts.run {
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

    fun updateProduct(product: Product) {
        try {
            _viewState.update { it.copy(currentProductUnderModification = product) }
            val updatedList = viewState.value.products.toMutableList()
            val index = updatedList.indexOfFirst { it.id == product.id }
            updatedList[index] = product

            viewModelScope.launch {
                productRepository.updateProduct(product)
                _viewState.update {
                    it.copy(
                        products = updatedList.toList(),
                        currentProductUnderModification = null
                    )
                }
                _events.emit(ProductEvent.ProductUpdated(product))
            }
        } catch (e: Exception) {
            _viewState.update { it.copy(error = e, currentProductUnderModification = null) }
        }

    }

    fun deleteProduct(product: Product) {
        try {
            _viewState.update { it.copy(currentProductUnderModification = product) }
            val updatedList = viewState.value.products.toMutableList()
            updatedList.remove(product)
            viewModelScope.launch {
                productRepository.deleteProduct(product)
                _viewState.update {
                    it.copy(
                        products = updatedList.toList(),
                        currentProductUnderModification = null
                    )
                }
                _events.emit(ProductEvent.ProductDeleted(product))
            }
        } catch (e: Exception) {
            _viewState.update { it.copy(error = e, currentProductUnderModification = null) }
        }
    }
}
