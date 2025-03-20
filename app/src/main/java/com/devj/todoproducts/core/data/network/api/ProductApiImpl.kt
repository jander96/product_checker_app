package com.devj.todoproducts.core.data.network.api

import com.devj.todoproducts.core.data.network.dto.ProductDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.path


class ProductApiImpl(private val client: HttpClient) : ProductApi {

    override suspend fun getProducts(): List<ProductDto> {
        val result =  client.get {
            url {
                path(ProductApiConstant.PRODUCT)
            }
        }

        return  result.body()
    }

    override suspend fun createProduct(productDto: ProductDto): ProductDto {
        val result =  client.post {
            url {
                path(ProductApiConstant.PRODUCT)
                setBody(productDto)
            }
        }

        return  result.body()
    }

    override suspend fun getProduct(id: Int): ProductDto {
        val result =  client.get {
            url {
                path(ProductApiConstant.PRODUCT)
                path(id.toString())
            }
        }

        return  result.body()
    }

    override suspend fun updateProduct(productDto: ProductDto): ProductDto {
        val result =  client.put("${ProductApiConstant.PRODUCT}/${productDto.id}") {
            url {
                setBody(productDto)
            }
        }

        return  result.body()
    }

    override suspend fun deleteProduct(product: ProductDto) {
        val result =  client.delete("${ProductApiConstant.PRODUCT}/${product.id}")

        return  result.body()
    }


}