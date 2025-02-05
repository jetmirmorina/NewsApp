package com.example.news.features_components.core.remote.modes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Suppress("PLUGIN_IS_NOT_ENABLED")
@Serializable
data class Source(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String = ""
)