package com.mnhyim.haxxernyius.data.network

import co.touchlab.kermit.Logger
import com.mnhyim.haxxernyius.data.network.dto.StoryDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class HackerNewsApiImpl(
    private val httpClient: HttpClient
) : HackerNewsApi {

    private val BASE_URL = "https://hacker-news.firebaseio.com/v0/"

    override suspend fun getNewestStories(): List<Long> {
        val stories: List<Long> = httpClient.get(BASE_URL + "newstories.json").body()
        Logger.d(messageString = "Result: $stories", tag = "${this::class.simpleName}")
        return stories
    }

    override suspend fun getBestStories(): List<Long> {
        val stories: List<Long> = httpClient.get(BASE_URL + "beststories.json").body()
        Logger.d(messageString = "Result: $stories", tag = "${this::class.simpleName}")
        return stories
    }

    override suspend fun getTopStories(): List<Long> {
        val stories: List<Long> = httpClient.get(BASE_URL + "topstories.json").body()
        Logger.d(messageString = "Result: $stories", tag = "${this::class.simpleName}")
        return stories
    }

    override suspend fun getStoryDetail(id: Long): StoryDto {
        val story: StoryDto = httpClient.get(BASE_URL + "item/$id.json").body()
        Logger.d(messageString = "Item $id: $story", tag = "${this::class.simpleName}")
        return story
    }
}