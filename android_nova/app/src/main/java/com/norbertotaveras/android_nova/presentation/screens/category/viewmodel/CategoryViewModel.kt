package com.norbertotaveras.android_nova.presentation.screens.category.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import com.norbertotaveras.android_nova.domain.usecase.news.NewsUseCases
import com.norbertotaveras.android_nova.extensions.launchOnIODispatcher
import com.norbertotaveras.android_nova.presentation.screens.category.state.CategoryViewStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val category: String = savedStateHandle["category"] ?: throw IllegalArgumentException("Category not found")
    var viewStateHolder = CategoryViewStateHolder()

    init {
        val news = newsUseCases.headlinesNewsUseCase(
            country = "us",
            category = category
        ).cachedIn(viewModelScope)
        viewStateHolder.also {
            it.articles = news
            it.isLoading = false
        }
    }
}