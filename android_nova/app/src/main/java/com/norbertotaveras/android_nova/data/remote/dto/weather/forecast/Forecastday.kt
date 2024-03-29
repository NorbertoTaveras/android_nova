package com.norbertotaveras.android_nova.data.remote.dto.weather.forecast

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.norbertotaveras.android_nova.domain.model.forecast.ForecastDayDataModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forecastday(
    @SerializedName("astro")
    val astro: Astro,
    @SerializedName("date")
    val date: String,
    @SerializedName("date_epoch")
    val date_epoch: Int,
    @SerializedName("day")
    val day: Day,
    @SerializedName("hour")
    val hour: List<Hour>
): Parcelable {
    fun toForecastDayDataModel(): ForecastDayDataModel {
        return ForecastDayDataModel(
            astro = astro.toAstroDataModel(),
            date = date,
            date_epoch = date_epoch,
            day = day.toDayDataModel(),
            hour = hour.map { it.toHourDataModel() }
        )
    }
}