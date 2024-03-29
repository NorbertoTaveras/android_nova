package com.norbertotaveras.android_nova.presentation.screens.sources.viewmodel

import androidx.lifecycle.ViewModel
import com.norbertotaveras.android_nova.domain.usecase.news.NewsUseCases
import com.norbertotaveras.android_nova.extensions.launchOnIODispatcher
import com.norbertotaveras.android_nova.presentation.screens.sources.state.SourcesViewStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    var viewStateHolder = SourcesViewStateHolder()

    init {
        getData()
    }

    private fun getData() {
        launchOnIODispatcher {
            val sourcesDeferred = async(Dispatchers.IO) { newsUseCases.sourcesUseCase() }
            val sources = sourcesDeferred.await().getOrNull()?.sources ?: emptyList()
            viewStateHolder.also {
                it.sources = sources
                it.isLoading = false
            }
        }
    }
}