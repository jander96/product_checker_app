package com.devj.todoproducts.features.unreviewed.di


import com.devj.todoproducts.features.unreviewed.presenter.viewmodel.UnreviewedViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val unreviewedModule = module {
    viewModel { UnreviewedViewModel(get()) }
}