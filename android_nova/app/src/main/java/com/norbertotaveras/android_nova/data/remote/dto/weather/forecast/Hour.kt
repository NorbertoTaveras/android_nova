package com.norbertotaveras.android_nova.data.remote.dto.weather.forecast

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.norbertotaveras.android_nova.data.remote.dto.weather.current.Condition
import com.norbertotaveras.android_nova.domain.model.forecast.HourDataModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hour(
    @SerializedName("chance_of_rain")
    val chance_of_rain: Int,
    @SerializedName("chance_of_snow")
    val chance_of_snow: Int,
    @SerializedName("cloud")
    val cloud: Int,
    @SerializedName("condition")
    val condition: Condition,
    @SerializedName("dewpoint_c")
    val dewpoint_c: Double,
    @SerializedName("dewpoint_f")
    val dewpoint_f: Double,
    @SerializedName("diff_rad")
    val diff_rad: Double,
    @SerializedName("feelslike_c")
    val feelslike_c: Double,
    @SerializedName("feelslike_f")
    val feelslike_f: Double,
    @SerializedName("gust_kph")
    val gust_kph: Double,
    @SerializedName("gust_mph")
    val gust_mph: Double,
    @SerializedName("heatindex_c")
    val heatindex_c: Double,
    @SerializedName("heatindex_f")
    val heatindex_f: Double,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("is_day")
    val is_day: Int,
    @SerializedName("precip_in")
    val precip_in: Double,
    @SerializedName("precip_mm")
    val precip_mm: Double,
    @SerializedName("pressure_in")
    val pressure_in: Double,
    @SerializedName("pressure_mb")
    val pressure_mb: Double,
    @SerializedName("short_rad")
    val short_rad: Double,
    @SerializedName("snow_cm")
    val snow_cm: Double,
    @SerializedName("temp_c")
    val temp_c: Double,
    @SerializedName("temp_f")
    val temp_f: Double,
    @SerializedName("time")
    val time: String,
    @SerializedName("time_epoch")
    val time_epoch: Int,
    @SerializedName("uv")
    val uv: Double,
    @SerializedName("vis_km")
    val vis_km: Double,
    @SerializedName("vis_miles")
    val vis_miles: Double,
    @SerializedName("will_it_rain")
    val will_it_rain: Int,
    @SerializedName("will_it_snow")
    val will_it_snow: Int,
    @SerializedName("wind_degree")
    val wind_degree: Int,
    @SerializedName("wind_dir")
    val wind_dir: String,
    @SerializedName("wind_kph")
    val wind_kph: Double,
    @SerializedName("wind_mph")
    val wind_mph: Double,
    @SerializedName("windchill_c")
    val windchill_c: Double,
    @SerializedName("windchill_f")
    val windchill_f: Double
): Parcelable {
    fun toHourDataModel(): HourDataModel {
        return HourDataModel(
            chance_of_rain = chance_of_rain,
            chance_of_snow = chance_of_snow,
            cloud = cloud,
            condition = condition.toConditionDataModel(),
            dewpoint_c = dewpoint_c,
            dewpoint_f = dewpoint_f,
            diff_rad = diff_rad,
            feelslike_c = feelslike_c,
            feelslike_f = feelslike_f,
            gust_kph = gust_kph,
            gust_mph = gust_mph,
            heatindex_c = heatindex_c,
            heatindex_f = heatindex_f,
            humidity = humidity,
            is_day = is_day,
            precip_in = precip_in,
            precip_mm = precip_mm,
            pressure_in = pressure_in,
            pressure_mb = pressure_mb,
            short_rad = short_rad,
            snow_cm = snow_cm,
            temp_c = temp_c,
            temp_f = temp_f,
            time = time,
            time_epoch = time_epoch,
            uv = uv,
            vis_km = vis_km,
            vis_miles = vis_miles,
            will_it_rain = will_it_rain,
            will_it_snow = will_it_snow,
            wind_degree = wind_degree,
            wind_dir = wind_dir,
            wind_kph = wind_kph,
            wind_mph = wind_mph,
            windchill_c = windchill_c,
            windchill_f = windchill_f
        )
    }
}