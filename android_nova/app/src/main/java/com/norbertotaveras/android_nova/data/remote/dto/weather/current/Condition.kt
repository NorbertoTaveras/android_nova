package com.norbertotaveras.android_nova.data.remote.dto.weather.current

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.norbertotaveras.android_nova.domain.model.weather.ConditionDataModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Condition(
    @SerializedName("author")
    val code: Int,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("text")
    val text: String
) : Parcelable {
    fun toConditionDataModel(): ConditionDataModel {
        return ConditionDataModel(
            code = code,
            icon = icon,
            text = text
        )
    }
}