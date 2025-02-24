package com.mnhyim.haxxernyius

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TestKtor(private val httpClient: HttpClient) : KoinComponent {

    suspend fun greeting(): String {
        val response = httpClient.get("https://ktor.io/docs/")
        return response.bodyAsText()
    }
}