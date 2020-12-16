package com.test.starwarstest.core

import android.app.Application
import com.test.starwarstest.di.apiModule
import com.test.starwarstest.di.networkModule
import com.test.starwarstest.di.repositoryModule
import com.test.starwarstest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FilmsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FilmsApplication)
            modules(
                apiModule,
                viewModelModule,
                repositoryModule,
                networkModule,
            )
        }
    }
}