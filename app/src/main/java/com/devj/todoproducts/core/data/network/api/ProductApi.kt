package com.devj.todoproducts.core.data.network.api

import com.devj.todoproducts.core.data.network.dto.ProductDto

interface ProductApi {
    suspend fun getProducts(): List<ProductDto>
    suspend fun createProduct(productDto: ProductDto): ProductDto
    suspend fun getProduct(id: Int): ProductDto
    suspend fun updateProduct(productDto: ProductDto): ProductDto
    suspend fun deleteProduct(id: ProductDto)
}