package com.devj.todoproducts.core.data.repository

import com.devj.todoproducts.core.domain.data.ProductRepository
import org.koin.dsl.module

val repositoryModule  = module {
    factory<ProductRepository> {
        ProductRepositoryImpl(get(), get())
    }
}
