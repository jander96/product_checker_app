package com.devj.todoproducts.features.reviewed.domain.usecase

import com.devj.todoproducts.core.domain.data.ProductRepository
import com.devj.todoproducts.core.domain.model.PaginatedResponse

class GetReviewedProducts(private val repository: ProductRepository) {

    suspend operator fun invoke(offset: Int, limit: Int): PaginatedResponse {
        val products = repository.getProductsLocal(offset, limit)
        return products.copy(products = products.products.filter { it.approved })
    }

}