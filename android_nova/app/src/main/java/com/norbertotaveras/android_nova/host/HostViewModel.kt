package com.norbertotaveras.android_nova.host

import android.content.Context
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.domain.manager.LocationManager
import com.norbertotaveras.android_nova.domain.usecase.app.AppEntryUseCases
import com.norbertotaveras.android_nova.domain.usecase.news.NewsUseCases
import com.norbertotaveras.android_nova.domain.usecase.weather.GetCurrentWeatherUseCase
import com.norbertotaveras.android_nova.domain.usecase.weather.WeatherUseCases
import com.norbertotaveras.android_nova.extensions.launchOnDefaultDispatcher
import com.norbertotaveras.android_nova.extensions.launchOnIODispatcher
import com.norbertotaveras.android_nova.presentation.screens.navigation.Route
import com.norbertotaveras.android_nova.utils.location.LocationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HostViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases,
    private val appEntryUseCases: AppEntryUseCases,
    private val locationManager: LocationManager
) : ViewModel() {
    /*val headlinesNews: Flow<PagingData<Article>> =
        newsUseCases.headlinesNewsUseCase(country = "us").cachedIn(viewModelScope)*/

    /*val news = newsUseCases.getNewsUseCase(
        sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
    ).cachedIn(viewModelScope)*/

    private var _news: Flow<PagingData<Article>>? = null
    val news: Flow<PagingData<Article>>?
        get() = _news

    val viewStateHolder = HostViewStateHolder()

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        appEntryUseCases.readAppEntryUseCase().onEach { shouldStartFromHomeScreen ->
            startDestination = if (shouldStartFromHomeScreen) {
                Route.NewsNavigation.route
            } else {
                Route.AppStartNavigation.route
            }
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }

    private fun loadData() {
        launchOnIODispatcher {
            val sourcesDeferred = async(Dispatchers.IO) { newsUseCases.sourcesUseCase() }
            val sources = sourcesDeferred.await().getOrNull()?.sources
            loadNews(sources = sources?.map { source -> source.name } ?: emptyList())
            viewStateHolder.also {
                it.sources = sources ?: emptyList()
                it.isLoading = false
            }
        }
    }

    private fun loadNews(sources: List<String>) {
        _news = newsUseCases.getNewsUseCase(
            sources = sources,
            language = ""
        ).cachedIn(viewModelScope)
    }

    fun updateLocation(context: Context) {
        launchOnDefaultDispatcher {
            val locationResult = LocationHelper.getCurrentLocation(context)
            locationResult.onSuccess { location ->
                locationManager.storeLocation(context, location.latitude, location.longitude)
            }.onFailure { exception ->
                locationManager.storeLocation(context, 37.4221, 122.0852)
                Log.d("#Location Failure", exception.toString())
            }
        }
    }

    fun locationUpdates(context: Context) {
        launchOnDefaultDispatcher {
            LocationHelper.startLocationUpdates(
                context,
                10000L,
                5000L).collect { result ->
                result.onSuccess { location ->
                    locationManager.storeLocation(context, location.latitude, location.longitude)
                }.onFailure { exception ->
                    locationManager.storeLocation(context, 37.4221, 122.0852)
                    Log.d("#Location Failure", exception.toString())
                }
            }
        }
    }
}