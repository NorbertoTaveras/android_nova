package com.norbertotaveras.android_nova.domain.model.forecast

import android.os.Parcelable
import com.norbertotaveras.android_nova.domain.model.weather.CurrentDataModel
import com.norbertotaveras.android_nova.domain.model.weather.LocationDataModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastWeatherResponseDataModel(
    val current: CurrentDataModel,
    val forecast: ForecastDataModel,
    val location: LocationDataModel
): Parcelable