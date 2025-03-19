package com.devj.todoproducts.features.unreviewed.domain.usecase

import com.devj.todoproducts.core.domain.data.ProductRepository
import com.devj.todoproducts.core.domain.model.PaginatedResponse

class GetUnreviewedProducts(private val repository: ProductRepository) {

    suspend operator fun invoke(offset: Int, limit: Int): PaginatedResponse {
        val products = repository.getProductsRemote(offset, limit)
        return products.copy(products = products.products.filter { !it.approved })
    }

}