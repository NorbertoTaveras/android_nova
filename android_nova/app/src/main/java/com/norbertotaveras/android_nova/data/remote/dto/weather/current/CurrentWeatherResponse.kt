package com.norbertotaveras.android_nova.data.remote.dto.weather.current

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.norbertotaveras.android_nova.domain.model.weather.CurrentWeatherResponseDataModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentWeatherResponse(
    @SerializedName("current")
    val current: Current,
    @SerializedName("location")
    val location: Location
) : Parcelable {
    fun toCurrentWeatherResponseDataModel(): CurrentWeatherResponseDataModel {
        return CurrentWeatherResponseDataModel(
            current = current.toCurrentDataModel(),
            location = location.toLocationDataModel()
        )
    }
}