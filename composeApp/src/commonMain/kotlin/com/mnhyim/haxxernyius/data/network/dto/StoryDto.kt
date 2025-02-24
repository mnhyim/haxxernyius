package com.mnhyim.haxxernyius.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class StoryDto(
    val id: Long,
    val by: String,
    val score: Int,
    val time: Double,
    val title: String,
    val type: String,
    val url: String? = null,
    val text: String? = null,
    val descendants: Int,
)

