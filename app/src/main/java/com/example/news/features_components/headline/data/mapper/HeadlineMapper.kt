package com.example.news.features_components.headline.data.mapper

import com.example.news.features_components.domain.mapper.Mapper
import com.example.news.features_components.domain.models.NewsyArticle
import com.example.news.features_components.headline.data.local.model.HeadlineDto

class HeadlineMapper: Mapper<HeadlineDto, NewsyArticle> {
    override fun toModel(value: HeadlineDto): NewsyArticle {
        TODO("Not yet implemented")
    }

    override fun fromModel(value: NewsyArticle): HeadlineDto {
        TODO("Not yet implemented")
    }
}