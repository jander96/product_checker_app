package com.devj.todoproducts.core.data.local.datasource

import com.devj.todoproducts.core.data.local.db.daos.ProductDao
import com.devj.todoproducts.core.data.local.db.entities.ProductEntity

class CacheDatasourceImpl(private val productDao: ProductDao) : CacheDatasource {
    override suspend fun getAll(): List<ProductCache> {
        return productDao.getAll().map { it.toCache() }
    }

    override suspend fun findById(id: Int): ProductCache {
        return productDao.findById(id).toCache()
    }

    override suspend fun insertAll(vararg users: ProductCache) {
        return productDao.insertAll(*users.map { ProductEntity.fromCache(it) }.toTypedArray())
    }

    override suspend fun delete(user: ProductCache) {
        return productDao.delete(ProductEntity.fromCache(user))
    }
}