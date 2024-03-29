package com.norbertotaveras.android_nova.domain.usecase.bookmark

import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.domain.repository.news.NewsFwkRepository

class UpsertArticleUseCase(
    private val repository: NewsFwkRepository
) {
    suspend operator fun invoke(article: Article){
        repository.upsertArticle(article = article)
    }
}