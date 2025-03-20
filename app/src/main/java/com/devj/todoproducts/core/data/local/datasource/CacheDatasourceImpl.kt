package com.devj.todoproducts.core.data.local.datasource

import com.devj.todoproducts.core.data.local.db.daos.ProductDao
import com.devj.todoproducts.core.data.local.db.entities.ProductEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CacheDatasourceImpl(private val productDao: ProductDao) : CacheDatasource {
    override fun getAll(offset: Int, limit: Int): Flow<List<ProductCache>> {
        return productDao.getAll(offset,limit).map { it.map { it.toCache() } }
    }

    override suspend fun findById(id: Int): ProductCache {
        return productDao.findById(id).toCache()
    }

    override suspend fun insertAll(vararg products: ProductCache) {
        return productDao.insertAll(*products.map { ProductEntity.fromCache(it) }.toTypedArray())
    }

    override suspend fun delete(user: ProductCache) {
        return productDao.delete(ProductEntity.fromCache(user))
    }
}