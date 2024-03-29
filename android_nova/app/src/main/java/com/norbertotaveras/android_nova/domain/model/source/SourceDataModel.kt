package com.norbertotaveras.android_nova.domain.model.source

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceDataModel(
    val category: String,
    val country: String,
    val description: String,
    val id: String,
    val language: String,
    val name: String,
    val url: String
) : Parcelable
