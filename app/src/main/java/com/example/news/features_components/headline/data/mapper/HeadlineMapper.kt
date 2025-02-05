package com.example.news.features_components.headline.data.mapper

import com.example.news.features_components.domain.mapper.Mapper
import com.example.news.features_components.domain.models.NewsyArticle
import com.example.news.features_components.headline.data.local.model.HeadlineDto

class HeadlineMapper: Mapper<HeadlineDto, NewsyArticle> {
    override fun toModel(value: HeadlineDto): NewsyArticle {
        return value.run {
            NewsyArticle(
                id = id,
                author = author,
                content = content,
                description = description,
                publishedAt = publishedAt,
                source = source,
                title = title,
                url = url,
                urlToImage = urlToImage,
                favorite = favorite,
                category = category,
                page = page
            )
        }
    }

    override fun fromModel(value: NewsyArticle): HeadlineDto {
        return value.run {
            HeadlineDto(
                id = id,
                author = author,
                content = content,
                description = description,
                publishedAt = publishedAt,
                source = source,
                title = title,
                url = url,
                urlToImage = urlToImage,
                favourite = favorite,
                category = category,
                page = page
            )
        }
    }
}