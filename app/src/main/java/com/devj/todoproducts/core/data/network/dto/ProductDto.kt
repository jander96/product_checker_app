package com.devj.todoproducts.core.data.network.dto
import com.devj.todoproducts.core.domain.model.Product
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: Int,
    val createdAt: String,
    val name: String,
    val approved: Boolean,
) {
    companion object {
        fun fromDomain(cache: Product): ProductDto {
            return ProductDto(
                id = cache.id,
                createdAt = cache.createdDate,
                name = cache.name,
                approved = cache.approved,
            )
        }
    }
    fun toDomain(): Product {
        return Product(
            id = id,
            createdDate = createdAt,
            name = name,
            approved = approved,
        )
    }
}
