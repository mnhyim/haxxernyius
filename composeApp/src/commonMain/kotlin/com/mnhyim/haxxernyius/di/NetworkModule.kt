package com.mnhyim.haxxernyius.di

import co.touchlab.kermit.Logger
import com.mnhyim.haxxernyius.data.network.HackerNewsApi
import com.mnhyim.haxxernyius.data.network.HackerNewsApiImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            install(ResponseObserver) {
                onResponse { response ->
                    Logger.d("HTTP status: ${response.status.value}")
                }
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
}

val commonModule = module {
    singleOf(::HackerNewsApiImpl) { bind<HackerNewsApi>() }
}
