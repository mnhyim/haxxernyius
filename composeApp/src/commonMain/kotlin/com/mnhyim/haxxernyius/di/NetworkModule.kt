package com.mnhyim.haxxernyius.di

import co.touchlab.kermit.Logger
import com.mnhyim.haxxernyius.TestKtor
import io.ktor.client.HttpClient
import io.ktor.client.plugins.observer.ResponseObserver
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            install(ResponseObserver) {
                onResponse { response ->
                    Logger.d("HTTP status: ${response.status.value}")
                }
            }
        }
    }
}

val appModule = module {
    single { TestKtor() }
}