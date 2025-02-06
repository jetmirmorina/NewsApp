package com.example.news.features_components.headline.domain.use_cases

import com.example.news.features_components.domain.models.NewsyArticle
import com.example.news.features_components.headline.domain.repository.HeadlineRepository

class UpdateHeadlineFavoriteUseCase(
    private val repository: HeadlineRepository
) {
    suspend operator fun  invoke(article: NewsyArticle) {
        repository.updateFavouriteArticle(article)
    }
}