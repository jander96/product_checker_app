package com.devj.todoproducts.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface ToDoProductsScreen {
    @Serializable
    data object Reviewed: ToDoProductsScreen
    @Serializable
    data object Unreviewed: ToDoProductsScreen
}

