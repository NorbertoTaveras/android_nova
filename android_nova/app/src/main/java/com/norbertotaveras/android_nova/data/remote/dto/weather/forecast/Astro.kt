package com.norbertotaveras.android_nova.data.remote.dto.weather.forecast

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.norbertotaveras.android_nova.domain.model.forecast.AstroDataModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Astro(
    @SerializedName("is_moon_up")
    val is_moon_up: Int,
    @SerializedName("is_sun_up")
    val is_sun_up: Int,
    @SerializedName("moon_illumination")
    val moon_illumination: Int,
    @SerializedName("moon_phase")
    val moon_phase: String,
    @SerializedName("moonrise")
    val moonrise: String,
    @SerializedName("moonset")
    val moonset: String,
    @SerializedName("sunrise")
    val sunrise: String,
    @SerializedName("sunset")
    val sunset: String
) : Parcelable {
    fun toAstroDataModel(): AstroDataModel {
        return AstroDataModel(
            is_moon_up = is_moon_up,
            is_sun_up = is_sun_up,
            moon_illumination = moon_illumination,
            moon_phase = moon_phase,
            moonrise = moonrise,
            moonset = moonset,
            sunrise = sunrise,
            sunset = sunset
        )
    }
}