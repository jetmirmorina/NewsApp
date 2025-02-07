package com.example.news.features_presantations.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CaretScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.news.features_components.domain.models.NewsyArticle
import com.example.news.features_presantations.core.components.HeaderTitle
import com.example.news.features_presantations.core.components.PaginationLoadingItem
import com.example.news.features_presantations.core.components.itemSpacing
import com.example.news.features_presantations.home.components.HeadlineItem
import com.example.news.features_presantations.home.components.HomeTopAppBar
import com.example.news.features_presantations.home.viewmodel.HomeUIEvents
import com.example.news.features_presantations.home.viewmodel.HomeViewModel
import com.example.news.utils.ArticleCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onViewMoreClick: () -> Unit,
    onHeadlineItemClick:(id: Int) -> Unit,
    onSearch:() -> Unit,
    openDrawer:() -> Unit,
) {

    val homeState = viewModel.homeState
    val headlineArticles = homeState.headlineArticles.collectAsLazyPagingItems()
    val categories = ArticleCategory.values()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        topBar = {
            HomeTopAppBar(
                openDrawer = openDrawer,
                onSearch = onSearch
            )
        }
    ) { innerPadding ->

        LazyColumn(
            contentPadding = innerPadding
        ) {
            headlineItems(
                headlineArticles = headlineArticles,
                scope = scope,
                snackbarHostState = snackbarHostState,
                onViewMoreClick = onViewMoreClick,
                onHeadlineItemClick = onHeadlineItemClick,
                onFavouriteHeadlineChange = {
                    viewModel.onHomeUIEvents(
                        HomeUIEvents.OnHeadLineFavouriteChange(
                            article = it
                        )
                    )
                }
            )
        }


    }
}

private fun LazyListScope.headlineItems(
    headlineArticles: LazyPagingItems<NewsyArticle>,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    onViewMoreClick:() -> Unit,
    onHeadlineItemClick: (id: Int) -> Unit,
    onFavouriteHeadlineChange: (NewsyArticle) -> Unit,
) {
    item {
        HeaderTitle(
            title = "Hot News",
            icon = Icons.Default.LocationOn
        )

        Spacer(modifier = Modifier.size(itemSpacing))
    }

    item {
        headlineArticles.loadState.mediator?.refresh?.let {
            PaginationLoadingItem(
                pagingState = it,
                onError = {
                    scope.launch {

                        snackbarHostState.showSnackbar(
                           // message = e.message ?: "unknown error"
                            message = "unknown error"

                        )
                    }
                },
                onLoading = {
                    CircularProgressIndicator(
                        modifier = Modifier.fillMaxSize()
                            .wrapContentWidth(align = Alignment.CenterHorizontally)
                    )
                }
            )
        }
    }

    item {
        val articleList = headlineArticles.itemSnapshotList.items
        HeadlineItem(
          articles = articleList,
            articleCount = if (articleList.isNotEmpty()) 0 else articleList.lastIndex,
            onCardClick = {
                onHeadlineItemClick(it.id)
            },
            onViewMoreClick = onViewMoreClick,
            onFavoriteChange = onFavouriteHeadlineChange
        )

    }
}