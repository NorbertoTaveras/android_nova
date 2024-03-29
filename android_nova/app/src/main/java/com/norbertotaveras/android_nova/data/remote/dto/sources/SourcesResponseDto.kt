package com.norbertotaveras.android_nova.data.remote.dto.sources

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.norbertotaveras.android_nova.domain.model.source.SourceDataModel
import com.norbertotaveras.android_nova.domain.model.source.SourceResponseDataModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourcesResponseDto(
    @SerializedName("sources")
    val sources: List<SourceDto>,
    @SerializedName("status")
    val status: String
) : Parcelable {
    fun toSourceResponseDateModel(): SourceResponseDataModel {
        return SourceResponseDataModel(
            sources = sources.map { it.toSourceDataModel() } ?: emptyList(),
            status = status
        )
    }
}