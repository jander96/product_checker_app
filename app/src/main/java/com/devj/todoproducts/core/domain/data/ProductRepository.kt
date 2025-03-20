package com.devj.todoproducts.core.domain.data
import com.devj.todoproducts.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProductsRemote(offset: Int, limit: Int):  List<Product>
    fun getProductsLocal(offset: Int, limit: Int): Flow<List<Product>>
    suspend fun deleteProduct(product: Product, includeRemote : Boolean = true)
    suspend fun createProduct(product: Product)
    suspend fun updateProduct(product: Product)
}