package com.test.starwarstest.di

import com.test.starwarstest.api.FilmsApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideCountriesApi(retrofit: Retrofit): FilmsApi {
        return retrofit.create(FilmsApi::class.java)
    }
    single { provideCountriesApi(get()) }

}