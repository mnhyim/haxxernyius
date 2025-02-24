package com.mnhyim.haxxernyius.data.network

import com.mnhyim.haxxernyius.data.network.dto.StoryDto
import io.ktor.client.statement.HttpResponse

interface HackerNewsApi {

    suspend fun getNewestStories(): List<Long>
    suspend fun getBestStories(): List<Long>
    suspend fun getTopStories(): List<Long>
    suspend fun getStoryDetail(id: Long): StoryDto
}