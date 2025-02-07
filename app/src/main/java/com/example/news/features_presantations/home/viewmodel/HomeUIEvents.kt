package com.example.news.features_presantations.home.viewmodel

import com.example.news.features_components.domain.models.NewsyArticle
import com.example.news.utils.ArticleCategory

sealed class HomeUIEvents{
    object ViewMoreClicked:HomeUIEvents()
    data class ArticleClicked(val url:String):HomeUIEvents()
    data class CategoryChange(val category: ArticleCategory) : HomeUIEvents()
    data class PreferencePanelToggle(val isOpen: Boolean) : HomeUIEvents()
    data class OnHeadLineFavouriteChange(val article: NewsyArticle) : HomeUIEvents()
    data class OnDiscoverFavouriteChange(val article: NewsyArticle) : HomeUIEvents()
}