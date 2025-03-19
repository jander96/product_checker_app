package com.devj.todoproducts.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devj.todoproducts.core.data.local.db.daos.ProductDao
import com.devj.todoproducts.core.data.local.db.entities.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class ToDoProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}