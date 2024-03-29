package com.norbertotaveras.android_nova.domain.usecase.news

import com.norbertotaveras.android_nova.domain.usecase.bookmark.DeleteArticleUseCase
import com.norbertotaveras.android_nova.domain.usecase.bookmark.GetArticleUseCase
import com.norbertotaveras.android_nova.domain.usecase.bookmark.GetArticlesUseCase
import com.norbertotaveras.android_nova.domain.usecase.bookmark.UpsertArticleUseCase
import com.norbertotaveras.android_nova.domain.usecase.sources.GetSourcesUseCase
import javax.inject.Inject

data class NewsUseCases @Inject constructor(
    val getNewsUseCase: GetNewsUseCase,
    val headlinesNewsUseCase: GetHeadlinesNewsUseCase,
    val sourcesUseCase: GetSourcesUseCase,
    val searchNewsUseCase: SearchNewsUseCase,
    val upsertArticleUseCase: UpsertArticleUseCase,
    val deleteArticleUseCase: DeleteArticleUseCase,
    val getArticlesUseCase: GetArticlesUseCase,
    val getArticleUseCase: GetArticleUseCase
)
