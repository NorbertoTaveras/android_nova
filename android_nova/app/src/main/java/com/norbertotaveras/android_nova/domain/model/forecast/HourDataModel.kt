package com.norbertotaveras.android_nova.domain.model.forecast

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.norbertotaveras.android_nova.data.remote.dto.weather.current.Condition
import com.norbertotaveras.android_nova.domain.model.weather.ConditionDataModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class HourDataModel(
    val chance_of_rain: Int,
    val chance_of_snow: Int,
    val cloud: Int,
    val condition: ConditionDataModel,
    val dewpoint_c: Double,
    val dewpoint_f: Double,
    val diff_rad: Double,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val gust_kph: Double,
    val gust_mph: Double,
    val heatindex_c: Double,
    val heatindex_f: Double,
    val humidity: Int,
    val is_day: Int,
    val precip_in: Double,
    val precip_mm: Double,
    val pressure_in: Double,
    val pressure_mb: Double,
    val short_rad: Double,
    val snow_cm: Double,
    val temp_c: Double,
    val temp_f: Double,
    val time: String,
    val time_epoch: Int,
    val uv: Double,
    val vis_km: Double,
    val vis_miles: Double,
    val will_it_rain: Int,
    val will_it_snow: Int,
    val wind_degree: Int,
    val wind_dir: String,
    val wind_kph: Double,
    val wind_mph: Double,
    val windchill_c: Double,
    val windchill_f: Double
): Parcelable