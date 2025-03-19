package com.devj.todoproducts.core.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: Int,
    val createdAt: String,
    val name: String,
    val approved: Boolean,
)
