package com.norbertotaveras.android_nova.presentation.screens.weather.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.paging.PagingData
import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.domain.model.forecast.ForecastDataModel
import com.norbertotaveras.android_nova.domain.model.source.SourceDataModel
import com.norbertotaveras.android_nova.domain.model.weather.CurrentDataModel
import com.norbertotaveras.android_nova.domain.model.weather.LocationDataModel
import com.norbertotaveras.android_nova.utils.base.BaseViewStateHolder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class WeatherViewStateHolder : BaseViewStateHolder(isLoading = true) {
    var current by mutableStateOf<CurrentDataModel?>(null)
    var location by mutableStateOf<LocationDataModel?>(null)
    var forecast by mutableStateOf<ForecastDataModel?>(null)
}