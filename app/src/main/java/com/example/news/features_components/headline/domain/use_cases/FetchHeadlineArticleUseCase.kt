package com.example.news.features_components.headline.domain.use_cases

import androidx.paging.PagingData
import com.example.news.features_components.domain.models.NewsyArticle
import com.example.news.features_components.headline.domain.repository.HeadlineRepository
import kotlinx.coroutines.flow.Flow

class FetchHeadlineArticleUseCase(
    private val repository: HeadlineRepository
) {
    operator fun invoke(
        category: String,
        countryCode: String,
        language: String
    ): Flow<PagingData<NewsyArticle>> = repository.fetchHeadlineArticle(
        category, countryCode, language
    )
}