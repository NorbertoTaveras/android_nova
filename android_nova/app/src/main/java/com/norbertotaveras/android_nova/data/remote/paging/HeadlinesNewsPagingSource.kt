package com.norbertotaveras.android_nova.data.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.data.remote.service.news.NewsFwkService
import okio.IOException
import retrofit2.HttpException

class HeadlinesNewsPagingSource(
    private val service: NewsFwkService,
    private val country: String,
    private val category: String
): PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val response = service.getHeadlinesNews(
                country = country,
                category = category,
                page = page)
            val articles = response.articles
                .filter { it.title.isNotEmpty() && !it.content.isNullOrEmpty()}
                .distinctBy { it.title }
            val endOfPaginationReached = articles.size < params.loadSize
            LoadResult.Page(
                data = articles,
                nextKey = if (endOfPaginationReached) null else page + 1,
                prevKey = if (page == 1) null else page - 1
            )
        } catch (e: IOException) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}