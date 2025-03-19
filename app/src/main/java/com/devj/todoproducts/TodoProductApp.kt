package com.devj.todoproducts

import android.app.Application
import com.devj.todoproducts.core.data.local.di.localModule
import com.devj.todoproducts.core.data.network.di.networkModule
import com.devj.todoproducts.core.data.repository.repositoryModule
import com.devj.todoproducts.features.reviewed.di.reviewedModule
import com.devj.todoproducts.features.unreviewed.di.unreviewedModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TodoProductApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@TodoProductApp)
            modules(networkModule, localModule, repositoryModule, unreviewedModule, reviewedModule)
        }
    }
}