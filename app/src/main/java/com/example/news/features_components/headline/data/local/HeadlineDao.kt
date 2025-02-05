package com.example.news.features_components.headline.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Insert
import androidx.room.RoomDatabase
import androidx.room.Update
import com.example.news.features_components.headline.data.local.model.HeadlineDto
import kotlinx.coroutines.flow.Flow


@Dao
interface HeadlineDao {
    @Query("SELECT * FROM headline_table")
    fun getAllHeadlineArticles(): PagingSource<Int, HeadlineDto>

    @Query("SELECT * FROM headline_table WHERE id=:id")
    fun getHeadlineArticle(id: Int): Flow<HeadlineDto>


    @Query(
        "DELETE FROM headline_table WHERE favourite=0"
    )
    suspend fun removeAllHeadlineArticles()

    @Delete
    suspend fun removeFavouriteArticle(headlineDto: HeadlineDto)

    @Query(
        "UPDATE headline_table SET favourite =:isFavourite WHERE id=:id"
    )
    suspend fun updateFavouriteArticle(isFavourite: Boolean, id: Int)

}
