package com.norbertotaveras.android_nova.presentation.screens.details.event

import com.norbertotaveras.android_nova.data.remote.dto.articles.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()
    data class CheckArticleSaved(val url: String) : DetailsEvent()
    data object RemoveSideEffect : DetailsEvent()
}