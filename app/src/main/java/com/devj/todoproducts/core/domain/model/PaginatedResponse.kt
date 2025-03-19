package com.devj.todoproducts.core.domain.model

data class PaginatedResponse(
    val currentPage: Int = 0,
    val totalPages: Int = 0,
    val products: List<Product> = emptyList()
)