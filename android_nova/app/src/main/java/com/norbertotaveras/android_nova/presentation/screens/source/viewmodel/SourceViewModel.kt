package com.norbertotaveras.android_nova.presentation.screens.source.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import com.norbertotaveras.android_nova.domain.usecase.news.NewsUseCases
import com.norbertotaveras.android_nova.extensions.launchOnIODispatcher
import com.norbertotaveras.android_nova.presentation.screens.category.state.CategoryViewStateHolder
import com.norbertotaveras.android_nova.presentation.screens.source.state.SourceViewStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val id: String = savedStateHandle["id"] ?: throw IllegalArgumentException("id not found")
    var viewStateHolder = SourceViewStateHolder()

    init {
        val news = newsUseCases.getNewsUseCase(
            language = "",
            sources = listOf(id)
        ).cachedIn(viewModelScope)
        viewStateHolder.also {
            it.articles = news
            it.isLoading = false
        }
    }
}