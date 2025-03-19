package com.devj.todoproducts.core.domain.data

import com.devj.todoproducts.core.domain.model.PaginatedResponse
import com.devj.todoproducts.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProductsRemote(offset: Int, limit: Int): PaginatedResponse
    suspend fun getProductsLocal(offset: Int, limit: Int): PaginatedResponse
    suspend fun deleteProduct(product: Product)
    suspend fun createProduct(product: Product)
    suspend fun updateProduct(product: Product)
}