package com.example.news.features_components.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.news.features_components.headline.data.local.dao.HeadlineDao
import com.example.news.features_components.headline.data.local.dao.HeadlineRemoteKeyDao
import com.example.news.features_components.headline.data.local.model.HeadlineRemoteKey

@Database(
    entities = [
        HeadlineDao::class,
        HeadlineRemoteKeyDao:: class
               ],
    exportSchema = false,
    version = 1
)
abstract class NewsyArticleDatabase: RoomDatabase() {
    abstract fun headlineDao(): HeadlineDao
    abstract fun headlineRemoteDao(): HeadlineRemoteKeyDao
}