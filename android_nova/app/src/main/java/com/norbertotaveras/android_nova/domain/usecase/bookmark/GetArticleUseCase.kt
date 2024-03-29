package com.norbertotaveras.android_nova.domain.usecase.bookmark

import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.domain.repository.news.NewsFwkRepository

class GetArticleUseCase (
    private val repository: NewsFwkRepository
) {
    suspend operator fun invoke(url: String): Article?{
        return repository.getArticle(url = url)
    }
}