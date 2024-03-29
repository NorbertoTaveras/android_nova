package com.norbertotaveras.android_nova.presentation.screens.search.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.norbertotaveras.android_nova.domain.manager.LocationManager
import com.norbertotaveras.android_nova.domain.usecase.news.NewsUseCases
import com.norbertotaveras.android_nova.domain.usecase.weather.WeatherUseCases
import com.norbertotaveras.android_nova.extensions.launchOnIODispatcher
import com.norbertotaveras.android_nova.presentation.screens.home.state.HomeViewStateHolder
import com.norbertotaveras.android_nova.presentation.screens.search.event.SearchEvent
import com.norbertotaveras.android_nova.presentation.screens.search.state.SearchState
import com.norbertotaveras.android_nova.presentation.screens.search.state.SearchViewStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases,
) : ViewModel() {

    private var _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    init {
        getData()
    }

    private fun getData() {
        launchOnIODispatcher {
            val sourcesDeferred = async(Dispatchers.IO) { newsUseCases.sourcesUseCase() }
            val sources = sourcesDeferred.await().getOrNull()?.sources ?: emptyList()
            val names = sources.map { source -> source.name }
            _state.value = _state.value.copy(names = names)
        }
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
            }
            is SearchEvent.SearchNews -> {
                launchOnIODispatcher {
                    searchNews()
                }
            }
        }
    }

    private fun searchNews() {
        val articles = newsUseCases.searchNewsUseCase(
            searchQuery = _state.value.searchQuery,
            sources = _state.value.names ?: emptyList()
        ).cachedIn(viewModelScope)
        _state.value = _state.value.copy(articles = articles)
    }
}