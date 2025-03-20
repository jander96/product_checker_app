package com.devj.todoproducts.core.data.local.db.entities

import android.annotation.SuppressLint
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devj.todoproducts.core.data.local.datasource.ProductCache
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val createdDate: Long,
    val updatedDate: Long,
    val name: String,
    val approved: Boolean,
    val checked: Boolean,
) {
    companion object {
        @SuppressLint("ConstantLocale")
        private val dateFormatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .withLocale(Locale.getDefault())
            .withZone(java.time.ZoneOffset.UTC)

        fun fromCache(cache: ProductCache): ProductEntity {
            return ProductEntity(
                id = cache.id,
                createdDate = parseDate(cache.createdDate),
                updatedDate = parseDate(cache.updatedDate),
                name = cache.name,
                approved = cache.approved,
                checked = cache.checked
            )
        }

        private fun parseDate(dateString: String): Long {
            return try {
                Instant.from(dateFormatter.parse(dateString)).toEpochMilli()
            } catch (e: DateTimeParseException) {
                0L
            }
        }
    }

    fun toCache(): ProductCache {
        return ProductCache(
            id = id,
            createdDate = formatDate(createdDate),
            updatedDate = formatDate(updatedDate),
            name = name,
            approved = approved,
            checked = checked
        )
    }

    private fun formatDate(timestamp: Long): String {
        return try {
            dateFormatter.format(Instant.ofEpochMilli(timestamp))
        } catch (e: Exception) {
            ""
        }
    }
}