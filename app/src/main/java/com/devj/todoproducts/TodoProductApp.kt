package com.devj.todoproducts

import android.app.Application
import com.devj.todoproducts.core.data.local.di.localModule
import com.devj.todoproducts.core.data.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TodoProductApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@TodoProductApp)
            modules(networkModule, localModule)
        }
    }
}