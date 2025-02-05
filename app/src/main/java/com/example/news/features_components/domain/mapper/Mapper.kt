package com.example.news.features_components.domain.mapper

interface Mapper<T: Any?, Model: Any> {
    fun toModel(value: T): Model
    fun fromModel(value: Model): T
}