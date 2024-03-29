package com.norbertotaveras.android_nova.data.remote.dto.weather.current

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.norbertotaveras.android_nova.domain.model.weather.LocationDataModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    @SerializedName("country")
    val country: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("localtime")
    val localtime: String,
    @SerializedName("localtime_epoch")
    val localtime_epoch: Int,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("tz_id")
    val tz_id: String
) : Parcelable {
    fun toLocationDataModel(): LocationDataModel {
        return LocationDataModel(
            country = country,
            lat = lat,
            localtime = localtime,
            localtime_epoch = localtime_epoch,
            lon = lon,
            name = name,
            region = region,
            tz_id = tz_id
        )
    }
}