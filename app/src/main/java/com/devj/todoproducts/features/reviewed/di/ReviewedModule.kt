package com.devj.todoproducts.features.reviewed.di

import com.devj.todoproducts.features.reviewed.presenter.viewmodel.ReviewedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val reviewedModule = module {

    viewModel { ReviewedViewModel(get()) }
}