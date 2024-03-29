package com.norbertotaveras.android_nova.data.remote.service.news

import com.norbertotaveras.android_nova.data.remote.dto.articles.NewsResponse
import com.norbertotaveras.android_nova.data.remote.dto.sources.SourcesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsFwkService {
    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("language") language: String
    ): NewsResponse

    @GET("top-headlines")
    suspend fun getHeadlinesNews(
        @Query("page") page: Int,
        @Query("country") country: String,
        @Query("category") category: String
    ): NewsResponse

    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("country") country: String
    ): SourcesResponseDto

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String
    ): NewsResponse
}