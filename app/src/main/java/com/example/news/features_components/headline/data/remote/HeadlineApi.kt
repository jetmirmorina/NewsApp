package com.example.news.features_components.headline.data.remote

import com.example.news.features_components.core.remote.modes.NewsyRemoteDto
import com.example.news.utils.K
import retrofit2.http.GET
import retrofit2.http.Query

interface HeadlineApi {
    companion object {
        private const val HEADLINE_END_POINT = "/v2/top-headlines"
    }

    @GET(HEADLINE_END_POINT)
    suspend fun getHeadlines(
        @Query("apiKey") key: String = K.API_KEY,
        @Query("category") category: String,
        @Query("country") country: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): NewsyRemoteDto



}