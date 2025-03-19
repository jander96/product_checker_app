package com.devj.todoproducts.features.unreviewed.di


import com.devj.todoproducts.features.unreviewed.domain.usecase.GetUnreviewedProducts
import com.devj.todoproducts.features.unreviewed.presenter.viewmodel.UnreviewedViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val unreviewedModule = module {
    factory { GetUnreviewedProducts(get())}
    viewModel { UnreviewedViewModel(get()) }
}