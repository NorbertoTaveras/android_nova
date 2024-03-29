package com.norbertotaveras.android_nova.presentation.screens.search.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.paging.PagingData
import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.utils.base.BaseViewStateHolder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null,
    val names: List<String>? = null
)

class SearchViewStateHolder : BaseViewStateHolder(isLoading = true) {
    var articles by mutableStateOf<Flow<PagingData<Article>>>(emptyFlow())
    var names by mutableStateOf<List<String>>(emptyList())
    var searchQuery by mutableStateOf("")
}