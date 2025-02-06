package com.example.news.features_components.headline.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.news.features_components.core.data.local.LocalContractDto

@Entity(tableName = "headline_table")
data class HeadlineDto(
    @PrimaryKey
    override val id: Int = 0,
    override val author: String,
    override val content: String,
    override val description: String,
    @ColumnInfo("published_at")
    override val publishedAt: String,
    override val source: String,
    override val title: String,
    override val url: String,
    @ColumnInfo("url_to_image")
    override val urlToImage: String,
    override val favorite: Boolean = false,
    override val category: String,
    override var page: Int
): LocalContractDto()