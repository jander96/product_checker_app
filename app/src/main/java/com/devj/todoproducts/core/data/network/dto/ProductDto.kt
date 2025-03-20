package com.devj.todoproducts.core.data.network.dto
import com.devj.todoproducts.core.domain.model.Product
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: Int,
    val createdAt: String,
    val updatedAt: String,
    val name: String,
    val approved: Boolean,
    val checked: Boolean,
) {
    companion object {
        fun fromDomain(domain: Product): ProductDto {
            return ProductDto(
                id = domain.id,
                createdAt = domain.createdDate,
                updatedAt = "",
                name = domain.name,
                approved = domain.approved,
                checked = domain.checked
            )
        }
    }
    fun toDomain(): Product {
        return Product(
            id = id,
            createdDate = createdAt,
            updatedDate = updatedAt,
            name = name,
            approved = approved,
            checked = checked
        )
    }
}
