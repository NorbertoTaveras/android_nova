package com.norbertotaveras.android_nova.data.remote.dto.weather.forecast

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.norbertotaveras.android_nova.data.remote.dto.weather.current.Condition
import com.norbertotaveras.android_nova.domain.model.forecast.DayDataModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Day(
    @SerializedName("avghumidity")
    val avghumidity: Int,
    @SerializedName("avgtemp_c")
    val avgtemp_c: Double,
    @SerializedName("avgtemp_f")
    val avgtemp_f: Double,
    @SerializedName("avgvis_km")
    val avgvis_km: Double,
    @SerializedName("avgvis_miles")
    val avgvis_miles: Double,
    @SerializedName("condition")
    val condition: Condition,
    @SerializedName("daily_chance_of_rain")
    val daily_chance_of_rain: Int,
    @SerializedName("daily_chance_of_snow")
    val daily_chance_of_snow: Int,
    @SerializedName("daily_will_it_rain")
    val daily_will_it_rain: Int,
    @SerializedName("daily_will_it_snow")
    val daily_will_it_snow: Int,
    @SerializedName("maxtemp_c")
    val maxtemp_c: Double,
    @SerializedName("maxtemp_f")
    val maxtemp_f: Double,
    @SerializedName("maxwind_kph")
    val maxwind_kph: Double,
    @SerializedName("maxwind_mph")
    val maxwind_mph: Double,
    @SerializedName("mintemp_c")
    val mintemp_c: Double,
    @SerializedName("mintemp_f")
    val mintemp_f: Double,
    @SerializedName("totalprecip_in")
    val totalprecip_in: Double,
    @SerializedName("totalprecip_mm")
    val totalprecip_mm: Double,
    @SerializedName("totalsnow_cm")
    val totalsnow_cm: Double,
    @SerializedName("uv")
    val uv: Double
): Parcelable {
    fun toDayDataModel(): DayDataModel {
        return DayDataModel(
            avghumidity = avghumidity,
            avgtemp_c = avgtemp_c,
            avgtemp_f = avgtemp_f,
            avgvis_km = avgvis_km,
            avgvis_miles = avgvis_miles,
            condition = condition.toConditionDataModel(),
            daily_chance_of_rain = daily_chance_of_rain,
            daily_chance_of_snow = daily_chance_of_snow,
            daily_will_it_rain = daily_will_it_rain,
            daily_will_it_snow = daily_will_it_snow,
            maxtemp_c = maxtemp_c,
            maxtemp_f = maxtemp_f,
            maxwind_kph = maxwind_kph,
            maxwind_mph = maxwind_mph,
            mintemp_c = mintemp_c,
            mintemp_f = mintemp_f,
            totalprecip_in = totalprecip_in,
            totalprecip_mm = totalprecip_mm,
            totalsnow_cm = totalsnow_cm,
            uv = uv
        )
    }
}