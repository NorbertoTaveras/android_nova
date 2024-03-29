package com.norbertotaveras.android_nova.data.repository.news

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.norbertotaveras.android_nova.data.local.dao.NewsDao
import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.data.remote.dto.sources.SourcesResponseDto
import com.norbertotaveras.android_nova.data.remote.paging.HeadlinesNewsPagingSource
import com.norbertotaveras.android_nova.data.remote.paging.NewsPagingSource
import com.norbertotaveras.android_nova.data.remote.paging.SearchNewsPagingSource
import com.norbertotaveras.android_nova.data.remote.service.news.NewsFwkService
import com.norbertotaveras.android_nova.domain.repository.news.NewsFwkRepository
import kotlinx.coroutines.flow.Flow

class NewsFwkRepositoryImpl(
    private val service: NewsFwkService,
    private val newsDao: NewsDao
) : NewsFwkRepository {
    override fun getNews(sources: List<String>, language: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    service = service,
                    language = language,
                    sources = sources.joinToString(separator = ","),
                )
            }
        ).flow
    }

    override fun getHeadlineNews(
        country: String,
        category: String
    ): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 20, initialLoadSize = 20),
            pagingSourceFactory = {
                HeadlinesNewsPagingSource(
                    service = service,
                    category = category ,
                    country = country
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery = searchQuery,
                    service = service,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override suspend fun getSources(country: String): SourcesResponseDto {
        return service.getSources(country = country)
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun getArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

    override suspend fun getArticle(url: String): Article? {
        return newsDao.getArticle(url = url)
    }
}