package com.example.news.features_components.headline.data.local.mapper

import com.example.news.features_components.core.remote.modes.Article
import com.example.news.features_components.domain.mapper.Mapper
import com.example.news.features_components.headline.data.local.model.HeadlineDto

class ArticleHeadlineDtoMapper(
    private val page: Int = 0,
    private val category: String = ""
): Mapper<Article, HeadlineDto> {

    override fun toModel(value: Article): HeadlineDto {
        return value.run {
            HeadlineDto(
                author = formatEmptyValue(author, "author"),
                content = formatEmptyValue(content, "content"),
                description = formatEmptyValue(description, "description"),
                publishedAt = publishedAt,
                source = source.name,
                title = title,
                url = url,
                urlToImage = urlToImage,
                page = page,
                category = category
            )
        }
    }

    override fun fromModel(value: HeadlineDto): Article {
        return value.run {
            Article(author, content, description, publishedAt)
        }
    }

    private fun formatEmptyValue(value: String?, default: String = ""): String {
        return value ?: "Unknown $default"
    }
}