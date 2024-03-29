package com.norbertotaveras.android_nova.domain.model.weather

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConditionDataModel(
    val code: Int,
    val icon: String,
    val text: String
) : Parcelable {
    val iconUrl: String
        get() = "https:$icon"
}