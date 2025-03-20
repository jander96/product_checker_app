package com.devj.todoproducts.core.data.local.datasource
import com.devj.todoproducts.core.domain.model.Product

data class ProductCache(
    val id: Int,
    val createdDate: String,
    val updatedDate: String,
    val name: String,
    val approved: Boolean,
    val checked: Boolean,
){
    companion object {
        fun fromDomain(domain: Product): ProductCache {
            return ProductCache(
                id = domain.id,
                createdDate = domain.createdDate,
                name = domain.name,
                approved = domain.approved,
                checked = domain.checked,
                updatedDate = domain.updatedDate
            )
        }
    }
    fun toDomain(): Product {
        return Product(
            id = id,
            createdDate = createdDate,
            name = name,
            approved = approved,
            checked = checked,
            updatedDate = updatedDate
        )
    }
}
