package com.devj.todoproducts.core.data.local.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devj.todoproducts.core.data.local.datasource.ProductCache

@Entity("product")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val createdDate: String,
    val name: String,
    val approved: Boolean,
) {
    companion object {
        fun fromCache(cache: ProductCache): ProductEntity {
            return ProductEntity(
                id = cache.id,
                createdDate = cache.createdDate,
                name = cache.name,
                approved = cache.approved,
            )
        }
    }
    fun toCache(): ProductCache {
        return ProductCache(
            id = id,
            createdDate = createdDate,
            name = name,
            approved = approved,
        )
    }
}

