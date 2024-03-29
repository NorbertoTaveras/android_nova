package com.norbertotaveras.android_nova.domain.usecase.bookmark

import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.domain.repository.news.NewsFwkRepository
import kotlinx.coroutines.flow.Flow

class GetArticlesUseCase(
    private val repository: NewsFwkRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return repository.getArticles()
    }
}