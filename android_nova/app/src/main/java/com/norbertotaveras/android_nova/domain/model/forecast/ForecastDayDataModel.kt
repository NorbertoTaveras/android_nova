package com.norbertotaveras.android_nova.domain.model.forecast

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.norbertotaveras.android_nova.domain.model.forecast.ForecastDataModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastDayDataModel(
    @SerializedName("astro")
    val astro: AstroDataModel,
    @SerializedName("date")
    val date: String,
    @SerializedName("date_epoch")
    val date_epoch: Int,
    @SerializedName("day")
    val day: DayDataModel,
    @SerializedName("hour")
    val hour: List<HourDataModel>
): Parcelable