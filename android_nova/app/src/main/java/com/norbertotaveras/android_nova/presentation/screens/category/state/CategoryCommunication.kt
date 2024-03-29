package com.norbertotaveras.android_nova.presentation.screens.category.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.paging.PagingData
import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.utils.base.BaseViewStateHolder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class CategoryViewStateHolder : BaseViewStateHolder(isLoading = true) {
    var articles by mutableStateOf<Flow<PagingData<Article>>>(emptyFlow())
    var category by mutableStateOf<String>("")
}