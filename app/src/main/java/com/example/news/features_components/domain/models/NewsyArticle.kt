package com.example.news.features_components.domain.models

data class NewsyArticle(
    override val id: Int,
    override val author: String,
    override val content: String,
    override val description: String,
    override val publishedAt: String,
    override val source: String,
    override val title: String,
    override val url: String,
    override val urlToImage: String,
    override val favorite: Boolean,
    override val category: String,
    override val page: Int

): DomainContract()
