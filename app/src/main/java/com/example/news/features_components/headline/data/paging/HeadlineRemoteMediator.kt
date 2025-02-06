package com.example.news.features_components.headline.data.paging

import android.icu.util.TimeUnit
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.news.features_components.core.data.local.NewsyArticleDatabase
import com.example.news.features_components.core.remote.modes.Article
import com.example.news.features_components.domain.mapper.Mapper
import com.example.news.features_components.domain.models.Country
import com.example.news.features_components.domain.models.Language
import com.example.news.features_components.headline.data.local.mapper.ArticleHeadlineDtoMapper
import com.example.news.features_components.headline.data.local.model.HeadlineDto
import com.example.news.features_components.headline.data.local.model.HeadlineRemoteKey
import com.example.news.features_components.headline.data.remote.HeadlineApi
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class HeadlineRemoteMediator(
    private val api: HeadlineApi,
    private val database: NewsyArticleDatabase,
    private val articleHeadlineDtoMapper: Mapper<Article, HeadlineDto>,
    private val category: String = "",
    private val country: String = "",
    private val language: String = ""
) : RemoteMediator<Int, HeadlineDto>() {

    override suspend fun initialize(): InitializeAction {

        val cacheTimeout = 20 * 60 * 1000L  // 1,200,000 ms

        return if (
            System.currentTimeMillis() - (database.headlineRemoteDao().getCreationTime()
                ?: 0) < cacheTimeout
        ) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }

    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, HeadlineDto>
    ): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                remoteKey?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKey = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKey?.prevKey
                prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = remoteKey != null
                )

            }
            LoadType.APPEND -> {
                val remoteKey = getRemoteKeyForLastItem(state)
                val nextKey = remoteKey?.nextKey
                nextKey ?: return  MediatorResult.Success (
                    endOfPaginationReached = remoteKey != null
                )
            }
        }

        return try {
            val headlineApiResponse = api.getHeadlines(
                pageSize = state.config.pageSize,
                category = category,
                page = page,
                country = country,
                language = language,
            )
            val headlineArticles = headlineApiResponse.articles
            val endOfPaginationReached = headlineArticles.isEmpty()
            database.apply {
                if (loadType == LoadType.REFRESH) {
                    headlineRemoteDao().clearRemoteKeys()
                    headlineDao().removeAllHeadlineArticles()
                }
            }

            val prevKey = if (page > 1) page-1 else null
            val nextKey = if (endOfPaginationReached) null else page+1
            val remoteKeys = headlineArticles.map { 
                HeadlineRemoteKey(
                    articleId = it.url,
                    prevKey = prevKey,
                    currentPage = nextKey,
                    nextKey = page
                )
            }

            database.apply {
                headlineRemoteDao().insertAll(remoteKeys)
                headlineDao().insertHeadlineArticle(
                    articles = headlineArticles.map {
                        articleHeadlineDtoMapper.toModel(
                            it
                        )
                    }
                )
            }

            MediatorResult.Success(false)

        } catch (error: IOException) {
            MediatorResult.Error(error)
        } catch (error: HttpException) {
            MediatorResult.Error(error)
        }
    }

/*
* This function retrieves the remote key (pagination metadata)
* for the first item in the current paging state.
* */
    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, HeadlineDto>
    ): HeadlineRemoteKey? {
        return state.pages.firstOrNull{
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let {article ->
            database.headlineRemoteDao().getRemoteKeyByArticleId(article.url)
        }
    }

    /*
    * Finds the remote key of the item closest to the user’s current position in the list.
 */
    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, HeadlineDto>
    ): HeadlineRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.url?.let { id ->
                database.headlineRemoteDao().getRemoteKeyByArticleId(id)
            }
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, HeadlineDto>
    ): HeadlineRemoteKey? {
        return state.pages.lastOrNull() {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { article ->
            database.headlineRemoteDao().getRemoteKeyByArticleId(article.url)
        }
    }
}