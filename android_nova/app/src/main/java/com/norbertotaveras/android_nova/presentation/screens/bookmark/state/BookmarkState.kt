package com.norbertotaveras.android_nova.presentation.screens.bookmark.state

import com.norbertotaveras.android_nova.data.remote.dto.articles.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
