package com.norbertotaveras.android_nova.host

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.norbertotaveras.android_nova.domain.model.source.SourceDataModel
import com.norbertotaveras.android_nova.utils.base.BaseViewStateHolder

class HostViewStateHolder : BaseViewStateHolder(isLoading = true) {
    var sources by mutableStateOf(emptyList<SourceDataModel>())
}