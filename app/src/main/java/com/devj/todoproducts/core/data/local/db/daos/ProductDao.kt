package com.devj.todoproducts.core.data.local.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devj.todoproducts.core.data.local.db.entities.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM product ORDER BY createdDate DESC LIMIT :limit OFFSET :offset")
    suspend fun getAll(offset: Int, limit: Int): List<ProductEntity>

    @Query("SELECT * FROM product WHERE id = :id")
    suspend fun findById(id: Int): ProductEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: ProductEntity)

    @Delete
    suspend fun delete(user: ProductEntity)
}