package com.norbertotaveras.android_nova.data.remote.dto.weather.current

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.norbertotaveras.android_nova.domain.model.weather.CurrentDataModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Current(
    @SerializedName("cloud")
    val cloud: Int,
    @SerializedName("condition")
    val condition: Condition,
    @SerializedName("feelslike_c")
    val feelslike_c: Double,
    @SerializedName("feeslike_f")
    val feelslike_f: Double,
    @SerializedName("gust_kph")
    val gust_kph: Double,
    @SerializedName("gust_mph")
    val gust_mph: Double,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("is_day")
    val is_day: Int,
    @SerializedName("last_updated")
    val last_updated: String,
    @SerializedName("last_updated_epoch")
    val last_updated_epoch: Int,
    @SerializedName("precip_in")
    val precip_in: Double,
    @SerializedName("precip_mm")
    val precip_mm: Double,
    @SerializedName("pressure_in")
    val pressure_in: Double,
    @SerializedName("pressure_mb")
    val pressure_mb: Double,
    @SerializedName("temp_c")
    val temp_c: Double,
    @SerializedName("temp_f")
    val temp_f: Double,
    @SerializedName("uv")
    val uv: Double,
    @SerializedName("vis_km")
    val vis_km: Double,
    @SerializedName("vis_miles")
    val vis_miles: Double,
    @SerializedName("wind_degree")
    val wind_degree: Int,
    @SerializedName("wind_dir")
    val wind_dir: String,
    @SerializedName("wind_kph")
    val wind_kph: Double,
    @SerializedName("wind_mph")
    val wind_mph: Double
) : Parcelable {
    fun toCurrentDataModel(): CurrentDataModel {
        return CurrentDataModel(
            cloud = cloud,
            condition = condition.toConditionDataModel(),
            feelslike_c = feelslike_c,
            feelslike_f = feelslike_f,
            gust_kph = gust_kph,
            gust_mph = gust_mph,
            humidity = humidity,
            is_day = is_day,
            last_updated = last_updated,
            last_updated_epoch = last_updated_epoch,
            precip_in = precip_in,
            precip_mm = precip_mm,
            pressure_in = pressure_in,
            pressure_mb = pressure_mb,
            temp_c = temp_c,
            temp_f = temp_f,
            uv = uv,
            vis_km = vis_km,
            vis_miles = vis_miles,
            wind_degree = wind_degree,
            wind_dir = wind_dir,
            wind_kph = wind_kph,
            wind_mph = wind_mph
        )
    }
}