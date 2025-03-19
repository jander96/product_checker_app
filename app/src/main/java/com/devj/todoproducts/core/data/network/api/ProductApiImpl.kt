package com.devj.todoproducts.core.data.network.api

import com.devj.todoproducts.core.data.network.dto.ProductDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path


class ProductApiImpl(private val client: HttpClient) : ProductApi {

    override suspend fun getProducts(): List<ProductDto> {
        val result =  client.get {
            url {
                path(ProductApiConstant.PRODUCTS)
            }
        }

        return  result.body()
    }

    override suspend fun createProduct(productDto: ProductDto): ProductDto {
        TODO("Not yet implemented")
    }

    override suspend fun getProduct(id: Int): ProductDto {
        TODO("Not yet implemented")
    }

    override suspend fun updateProduct(productDto: ProductDto): ProductDto {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProduct(id: ProductDto) {
        TODO("Not yet implemented")
    }


}