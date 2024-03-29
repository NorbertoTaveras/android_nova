package com.norbertotaveras.android_nova.domain.usecase.news

import androidx.paging.PagingData
import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.domain.repository.news.NewsFwkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHeadlinesNewsUseCase @Inject constructor(
    private val repository: NewsFwkRepository
) {
    operator fun invoke(country: String, category: String): Flow<PagingData<Article>> {
        return repository.getHeadlineNews(country = country, category = category)
    }
}