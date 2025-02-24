package com.mnhyim.haxxernyius.di

import com.mnhyim.haxxernyius.ui.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get()) }
}