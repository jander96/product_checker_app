package com.devj.todoproducts.core.domain.model

data class Product(
    val id: Int,
    val createdDate: String,
    val name: String,
    val approved: Boolean,
)
