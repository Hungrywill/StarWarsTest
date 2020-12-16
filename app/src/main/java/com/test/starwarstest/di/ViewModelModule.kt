package com.test.starwarstest.di

import com.test.starwarstest.viewmodels.FilmsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    // Specific viewModel pattern to tell Koin how to build CountriesViewModel
    viewModel {
        FilmsViewModel(repository = get())
    }

}