package com.norbertotaveras.android_nova.data.remote.dto.sources

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.norbertotaveras.android_nova.domain.model.source.SourceDataModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceDto(
    @SerializedName("category")
    val category: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) : Parcelable {
    fun toSourceDataModel(): SourceDataModel {
        return SourceDataModel(
            category = category,
            country = country,
            description = description,
            id = id,
            language = language,
            name = name,
            url = url
        )
    }
}