package com.norbertotaveras.android_nova.data.remote.dto.weather.forecast

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.norbertotaveras.android_nova.domain.model.forecast.ForecastDataModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forecast(
    @SerializedName("forecastday")
    val forecastDay: List<Forecastday>
): Parcelable {
    fun toForecastDayDataModel(): ForecastDataModel {
        return ForecastDataModel(
            forecastDay = forecastDay.map { it.toForecastDayDataModel() }
        )
    }
}