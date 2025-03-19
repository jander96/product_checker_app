package com.devj.todoproducts.features.reviewed.di

import com.devj.todoproducts.features.reviewed.domain.usecase.GetReviewedProducts
import org.koin.dsl.module

val reviewedModule = module {
    factory { GetReviewedProducts(get())}
}