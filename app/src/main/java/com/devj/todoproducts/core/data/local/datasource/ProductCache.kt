package com.devj.todoproducts.core.data.local.datasource
import com.devj.todoproducts.core.domain.model.Product

data class ProductCache(
    val id: Int,
    val createdDate: String,
    val name: String,
    val approved: Boolean,
){
    companion object {
        fun fromDomain(cache: Product): ProductCache {
            return ProductCache(
                id = cache.id,
                createdDate = cache.createdDate,
                name = cache.name,
                approved = cache.approved,
            )
        }
    }
    fun toDomain(): Product {
        return Product(
            id = id,
            createdDate = createdDate,
            name = name,
            approved = approved,
        )
    }
}
