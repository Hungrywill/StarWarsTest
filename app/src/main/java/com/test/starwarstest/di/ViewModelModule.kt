package com.test.starwarstest.di

import com.test.starwarstest.ui.viewmodels.FilmsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    // Specific viewModel pattern to tell Koin how to build FilmsViewModel
    viewModel {
        FilmsViewModel(repository = get())
    }

}