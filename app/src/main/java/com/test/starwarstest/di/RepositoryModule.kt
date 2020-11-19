package com.test.starwarstest.di

import android.content.Context
import com.test.starwarstest.api.FilmsApi
import com.test.starwarstest.repository.FilmsRepository
import com.test.starwarstest.repository.FilmsRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    fun provideCountryRepository(api: FilmsApi, context: Context): FilmsRepository {
        return FilmsRepositoryImpl(api, context)
    }
    single { provideCountryRepository(get(), androidContext()) }

}