package com.devj.todoproducts.core.data.repository

import com.devj.todoproducts.core.data.local.datasource.CacheDatasource
import com.devj.todoproducts.core.data.local.datasource.ProductCache
import com.devj.todoproducts.core.data.network.api.ProductApi
import com.devj.todoproducts.core.data.network.dto.ProductDto
import com.devj.todoproducts.core.domain.data.ProductRepository
import com.devj.todoproducts.core.domain.model.PaginatedResponse
import com.devj.todoproducts.core.domain.model.Product

class ProductRepositoryImpl(
    private val remoteDataSource: ProductApi,
    private val localDataSource: CacheDatasource
) : ProductRepository {
    override suspend fun getProductsRemote(
        offset: Int,
        limit: Int
    ): PaginatedResponse {
        val remote = remoteDataSource.getProducts().map { it.toDomain() }
        return PaginatedResponse(products = remote)
    }

    override suspend fun getProductsLocal(
        offset: Int,
        limit: Int
    ): PaginatedResponse {
        val local =  localDataSource.getAll(offset,limit).map { it.toDomain() }
        localDataSource.insertAll(*local.map { ProductCache.fromDomain(it) }.toTypedArray())
        return PaginatedResponse(products = local)
    }

    override suspend fun deleteProduct(product: Product) {
        remoteDataSource.deleteProduct(ProductDto.fromDomain(product))
        localDataSource.delete(ProductCache.fromDomain(product))
    }

    override suspend fun createProduct(product: Product) {
        remoteDataSource.createProduct(ProductDto.fromDomain(product))
        localDataSource.insertAll(ProductCache.fromDomain(product))
    }

    override suspend fun updateProduct(product: Product) {
        remoteDataSource.updateProduct(ProductDto.fromDomain(product))
        localDataSource.insertAll(ProductCache.fromDomain(product))
    }
}