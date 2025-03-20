package com.devj.todoproducts.features.reviewed.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devj.todoproducts.core.domain.data.ProductRepository
import com.devj.todoproducts.core.domain.model.Product
import com.devj.todoproducts.core.presenter.ProductEvent
import com.devj.todoproducts.core.presenter.ProductsState
import com.devj.todoproducts.core.presenter.composables.PaginationState
import com.devj.todoproducts.core.presenter.model.AsyncState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ReviewedViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    companion object {
        const val PAGE_SIZE = 7
    }

    private val _viewState: MutableStateFlow<ProductsState> =
        MutableStateFlow(ProductsState())
    val viewState: StateFlow<ProductsState> = _viewState.asStateFlow()
    private val _events = MutableSharedFlow<ProductEvent?>()
    val events: SharedFlow<ProductEvent?> get() = _events.asSharedFlow()
    private var offset = 0
    private val _productsFlow = MutableStateFlow<List<Product>>(emptyList())

    init {
        getProducts()
    }

    fun refresh() {
        offset = 0
        _viewState.update { ProductsState() }
        _productsFlow.value = emptyList()
        getProducts()
    }

    fun getProducts() {
        if (_viewState.value.paginationState == PaginationState.PAGINATING) return

        _viewState.update {
            it.copy(
                asyncState = AsyncState.LOADING,
                paginationState = if (offset == 0) PaginationState.LOADING else PaginationState.PAGINATING
            )
        }

        viewModelScope.launch {
            productRepository.getProductsLocal(offset, PAGE_SIZE)
                .onStart {
                    _viewState.update { it.copy(asyncState = AsyncState.LOADING) }
                }
                .catch { e ->
                    _viewState.update {
                        it.copy(
                            asyncState = AsyncState.ERROR,
                            error = e,
                            paginationState = PaginationState.ERROR
                        )
                    }
                }
                .collectLatest { incomingProducts ->
                    if (incomingProducts.isNotEmpty()) {
                        offset += PAGE_SIZE

                        _productsFlow.update { currentProducts ->
                            (currentProducts + incomingProducts)
                                .associateBy { it.id }
                                .values
                                .toList()
                        }
                    }

                    _viewState.update {
                        it.copy(
                            asyncState = AsyncState.SUCCESS,
                            products = _productsFlow.value, // Usa directamente la lista acumulada
                            paginationState = if (incomingProducts.size < PAGE_SIZE) {
                                PaginationState.PAGINATION_EXHAUSTED
                            } else {
                                PaginationState.IDLE
                            }
                        )
                    }
                }
        }
    }

    fun deleteProduct(product: Product) {
        try {
            _viewState.update { it.copy(currentProductUnderModification = product) }
            val updatedList = viewState.value.products.toMutableList()
            updatedList.remove(product)
            viewModelScope.launch {
                productRepository.deleteProduct(product, includeRemote = false)
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