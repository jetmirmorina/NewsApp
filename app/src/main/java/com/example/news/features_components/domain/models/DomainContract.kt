package com.example.news.features_components.domain.models

abstract class DomainContract {
    abstract val id: Int
    abstract val author: String
    abstract val content: String
    abstract val description: String
    abstract val publishedAt: String
    abstract val source: String
    abstract val title: String
    abstract val url: String
    abstract val urlToImage: String
    abstract val favorite: Boolean
    abstract val category: String
    abstract val page: Int

}