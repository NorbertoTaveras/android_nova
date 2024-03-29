package com.norbertotaveras.android_nova.domain.model.forecast

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastDataModel(
    val forecastDay: List<ForecastDayDataModel>
): Parcelable