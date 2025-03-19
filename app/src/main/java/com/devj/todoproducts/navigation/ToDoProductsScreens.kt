package com.devj.todoproducts.navigation

import androidx.compose.runtime.saveable.mapSaver
import kotlinx.serialization.Serializable

@Serializable
sealed interface ToDoProductsScreen {
    companion object {
        val RouteSaver = run {
            mapSaver(
                save = { screen ->
                    when (screen) {
                        is Reviewed -> mapOf("type" to "Reviewed")
                        is Unreviewed -> mapOf("type" to "Unreviewed")
                    }
                },
                restore = { map ->
                    when (map["type"] as? String) {
                        "Reviewed" -> Reviewed
                        "Unreviewed" -> Unreviewed
                        else -> null
                    }
                }
            )
        }
    }

    @Serializable
    data object Reviewed : ToDoProductsScreen

    @Serializable
    data object Unreviewed : ToDoProductsScreen
}

