package com.devj.todoproducts.core.data.local.di

import androidx.room.Room
import com.devj.todoproducts.core.data.local.datasource.CacheDatasource
import com.devj.todoproducts.core.data.local.datasource.CacheDatasourceImpl
import com.devj.todoproducts.core.data.local.db.ToDoProductDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {

    single {
        Room.databaseBuilder(
            androidContext(), ToDoProductDatabase::class.java, "todo_product_db"
        ).build()
    }

    single { get<ToDoProductDatabase>().productDao() }

    factory<CacheDatasource> {
        CacheDatasourceImpl(get())
    }
}