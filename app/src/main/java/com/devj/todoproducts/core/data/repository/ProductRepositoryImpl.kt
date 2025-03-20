package com.devj.todoproducts.core.data.repository

import com.devj.todoproducts.core.data.local.datasource.CacheDatasource
import com.devj.todoproducts.core.data.local.datasource.ProductCache
import com.devj.todoproducts.core.data.network.api.ProductApi
import com.devj.todoproducts.core.data.network.dto.ProductDto
import com.devj.todoproducts.core.domain.data.ProductRepository
import com.devj.todoproducts.core.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl(
    private val remoteDataSource: ProductApi,
    private val localDataSource: CacheDatasource
) : ProductRepository {
    override suspend fun getProductsRemote(
        offset: Int,
        limit: Int
    ): List<Product> {
        val remote = remoteDataSource.getProducts().map { it.toDomain() }
        return remote
    }

    override fun getProductsLocal(
        offset: Int,
        limit: Int
    ): Flow<List<Product>> {
        return  localDataSource.getAll(offset,limit).map { it.map { it.toDomain() } }
    }

    override suspend fun deleteProduct(product: Product, includeRemote: Boolean) {
        if(includeRemote){
            remoteDataSource.deleteProduct(ProductDto.fromDomain(product))
        }
        localDataSource.delete(ProductCache.fromDomain(product))
    }

    override suspend fun createProduct(product: Product) {
        val newProduct = remoteDataSource.createProduct(ProductDto.fromDomain(product))
        localDataSource.insertAll(ProductCache.fromDomain(newProduct.toDomain()))
    }

    override suspend fun updateProduct(product: Product) {
        val newProduct = remoteDataSource.updateProduct(ProductDto.fromDomain(product))
        localDataSource.insertAll(ProductCache.fromDomain(newProduct.toDomain()))
    }
}