package com.jlapps.modularizacao

import android.app.Application
import com.jlapps.data.di.dataModules
import com.jlapps.domain.di.domainModule
import com.jlapps.modularizacao.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)

            modules(domainModule + dataModules + listOf(presentationModule))
        }
    }
}

