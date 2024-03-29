package com.norbertotaveras.android_nova.domain.repository.news

import androidx.paging.PagingData
import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.data.remote.dto.sources.SourcesResponseDto
import com.norbertotaveras.android_nova.domain.model.source.SourceDataModel
import kotlinx.coroutines.flow.Flow

interface NewsFwkRepository {
    fun getNews(sources: List<String>, language: String): Flow<PagingData<Article>>
    fun getHeadlineNews(country: String, category: String): Flow<PagingData<Article>>
    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>
    fun getArticles(): Flow<List<Article>>
    suspend fun getSources(country: String): SourcesResponseDto
    suspend fun upsertArticle(article: Article)
    suspend fun deleteArticle(article: Article)
    suspend fun getArticle(url: String): Article?
}