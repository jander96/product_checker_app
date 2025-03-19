package com.devj.todoproducts.core.data.local.datasource


interface CacheDatasource {
    suspend fun getAll(): List<ProductCache>

    suspend fun findById(id: Int): ProductCache

    suspend fun insertAll(vararg users: ProductCache)

    suspend fun delete(user: ProductCache)
}