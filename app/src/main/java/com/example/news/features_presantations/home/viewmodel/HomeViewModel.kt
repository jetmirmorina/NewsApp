package com.example.news.features_presantations.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.news.features_components.headline.domain.use_cases.HeadlineUseCases
import com.example.news.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val headlineUseCases: HeadlineUseCases,
) : ViewModel() {
    var homeState by mutableStateOf(HomeState())
        private set


    init {
        loadArticles()
    }


    private fun loadArticles() {
        homeState = homeState.copy(
            headlineArticles =
            headlineUseCases.fetchHeadlineArticleUseCase(
                category = homeState.selectedCategory.category,
                countryCode = Utils.countryCodeList[0].code,
                language = Utils.languageCodeList[0].code
            ).cachedIn(viewModelScope)
        )
    }


    fun onHomeUIEvents(homeUIEvents: HomeUIEvents) {
        when (homeUIEvents) {
            HomeUIEvents.ViewMoreClicked -> {}
            is HomeUIEvents.ArticleClicked -> {}
            is HomeUIEvents.CategoryChange -> {}
            is HomeUIEvents.PreferencePanelToggle -> {}
            is HomeUIEvents.OnHeadLineFavouriteChange -> {
                viewModelScope.launch {
                    val isFavourite = homeUIEvents.article.favorite
                    val update = homeUIEvents.article.copy(
                        favorite = !isFavourite
                    )
                    headlineUseCases.updateHeadlineFavouriteUseCase(
                        update
                    )
                }
            }

            is HomeUIEvents.OnDiscoverFavouriteChange -> TODO()
        }
    }

}