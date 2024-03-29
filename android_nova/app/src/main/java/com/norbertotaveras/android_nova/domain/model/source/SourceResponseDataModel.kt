package com.norbertotaveras.android_nova.domain.model.source

data class SourceResponseDataModel(
    val sources: List<SourceDataModel>,
    val status: String
)
