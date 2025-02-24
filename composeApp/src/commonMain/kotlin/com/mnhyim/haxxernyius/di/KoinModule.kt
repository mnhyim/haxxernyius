package com.mnhyim.haxxernyius.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null): KoinApplication {
    return startKoin {
        config?.invoke(this)
        modules(
            networkModule,
            commonModule,
            appModule
        )
    }
}

