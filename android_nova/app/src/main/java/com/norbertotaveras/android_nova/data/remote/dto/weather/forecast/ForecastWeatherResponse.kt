package com.norbertotaveras.android_nova.data.remote.dto.weather.forecast

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.norbertotaveras.android_nova.data.remote.dto.weather.current.Current
import com.norbertotaveras.android_nova.data.remote.dto.weather.current.Location
import com.norbertotaveras.android_nova.domain.model.forecast.ForecastWeatherResponseDataModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastWeatherResponse(
    @SerializedName("current")
    val current: Current,
    @SerializedName("forecast")
    val forecast: Forecast,
    @SerializedName("location")
    val location: Location
): Parcelable {
    fun toForecastWeatherResponseDataModel(): ForecastWeatherResponseDataModel {
        return ForecastWeatherResponseDataModel(
            current = current.toCurrentDataModel(),
            forecast = forecast.toForecastDayDataModel(),
            location = location.toLocationDataModel()
        )
    }
}