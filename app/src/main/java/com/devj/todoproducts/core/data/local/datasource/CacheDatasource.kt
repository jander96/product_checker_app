package com.devj.todoproducts.core.data.local.datasource

import kotlinx.coroutines.flow.Flow


interface CacheDatasource {
    fun getAll(offset: Int, limit: Int): Flow<List<ProductCache>>

    suspend fun findById(id: Int): ProductCache

    suspend fun insertAll(vararg users: ProductCache)

    suspend fun delete(user: ProductCache)
}