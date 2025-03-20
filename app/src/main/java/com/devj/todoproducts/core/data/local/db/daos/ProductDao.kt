package com.devj.todoproducts.core.data.local.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devj.todoproducts.core.data.local.db.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("""
    SELECT * FROM product 
    WHERE checked = :checked 
    ORDER BY updatedDate ASC 
    LIMIT :limit OFFSET :offset
""")
    fun getAll(offset: Int, limit: Int , checked: Boolean = true): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE id = :id")
    suspend fun findById(id: Int): ProductEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: ProductEntity)

    @Delete
    suspend fun delete(user: ProductEntity)
}