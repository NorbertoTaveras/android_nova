package com.norbertotaveras.android_nova.presentation.screens.sources.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.paging.PagingData
import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.domain.model.source.SourceDataModel
import com.norbertotaveras.android_nova.utils.base.BaseViewStateHolder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class SourcesViewStateHolder : BaseViewStateHolder(isLoading = true) {
    var sources by mutableStateOf<List<SourceDataModel>>(emptyList())
}