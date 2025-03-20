package com.devj.todoproducts.core.presenter.composables

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
import com.devj.todoproducts.core.domain.model.Product

object ProductSaver {
    val Saver: Saver<Product, *> = mapSaver(
        save = { product ->
            mapOf(
                "id" to product.id,
                "createdDate" to product.createdDate,
                "updatedDate" to product.updatedDate,
                "name" to product.name,
                "approved" to product.approved,
                "checked" to product.checked,
            )
        },
        restore = { map ->
            Product(
                id = map["id"] as Int,
                createdDate = map["createdDate"] as String,
                updatedDate = map["updatedDate"] as String,
                name = map["name"] as String,
                approved = map["approved"] as Boolean,
                checked = map["checked"] as Boolean,
            )
        }
    )
}