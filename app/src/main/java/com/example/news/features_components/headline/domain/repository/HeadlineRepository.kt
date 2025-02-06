package com.example.news.features_components.headline.domain.repository

import androidx.paging.PagingData
import com.example.news.features_components.domain.models.NewsyArticle
import kotlinx.coroutines.flow.Flow

interface HeadlineRepository {
    fun fetchHeadlineArticle(
        category: String,
        country: String,
        language: String
    ): Flow<PagingData<NewsyArticle>>

    suspend fun updateFavouriteArticle(newsyArticle: NewsyArticle)



}