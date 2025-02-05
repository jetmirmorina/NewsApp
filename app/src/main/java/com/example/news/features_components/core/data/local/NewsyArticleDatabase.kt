package com.example.news.features_components.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.news.features_components.headline.data.local.HeadlineDao
import com.example.news.features_components.headline.data.local.model.HeadlineDto

@Database(
    entities = [],
    exportSchema = false,
    version = 1
)
abstract class NewsyArticleDatabase: RoomDatabase() {

}