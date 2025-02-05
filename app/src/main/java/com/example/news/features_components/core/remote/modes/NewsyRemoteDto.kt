package com.example.news.features_components.core.remote.modes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Suppress("PLUGIN_IS_NOT_ENABLED")
@Serializable
data class NewsyRemoteDto (
    @SerialName("articles")
    val articles: List<Article> = listOf(),
    @SerialName("status")
    val status: String = "",
    @SerialName("totalResults")
    val totalResults: Int = 0
)