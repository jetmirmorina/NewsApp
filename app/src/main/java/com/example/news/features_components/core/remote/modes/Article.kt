package com.example.news.features_components.core.remote.modes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Suppress("PLUGIN_IS_NOT_ENABLED")
@Serializable
data class Article(
    @SerialName("author")
    val author: String? = null,
    @SerialName("content")
    val content: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("publishedAt")
    val publishedAt: String = "",
    @SerialName("source")
    val source: Source = Source(),
    @SerialName("title")
    val title: String = "",
    @SerialName("url")
    val url: String = "",
    @SerialName("urlToImage")
    val urlToImage: String = ""
)