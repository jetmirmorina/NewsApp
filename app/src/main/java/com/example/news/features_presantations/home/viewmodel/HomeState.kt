package com.example.news.features_presantations.home.viewmodel

import androidx.paging.PagingData
import com.example.news.features_components.domain.models.NewsyArticle
import com.example.news.utils.ArticleCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeState(
    val headlineArticles: Flow<PagingData<NewsyArticle>> = emptyFlow<PagingData<NewsyArticle>>(),
    val selectedCategory: ArticleCategory = ArticleCategory.BUSINESS,
)
