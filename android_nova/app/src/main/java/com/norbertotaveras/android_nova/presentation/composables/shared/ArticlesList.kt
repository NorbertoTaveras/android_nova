package com.norbertotaveras.android_nova.presentation.composables.shared

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.utils.dimens.Dimens.ExtraSmallPadding2
import com.norbertotaveras.android_nova.utils.dimens.Dimens.MediumPadding24dp

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {
    val pagingResult = HandlePagingResult(articles = articles)
    if (pagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding24dp),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items( count = articles.itemCount) { index ->
                articles[index]?.let { article ->
                    MaterialArticleCard(
                        article = article,
                        onClick = { onClick(article) }
                    )
                }
            }
        }
    }
}

@Composable
fun MaterialArticlesList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {
    val pagingResult = HandlePagingResult(articles = articles)
    if (pagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items( count = articles.itemCount) { index ->
                articles[index]?.let { article ->
                    MaterialArticleCard(
                        article = article,
                        onClick = { onClick(article) }
                    )
                }
            }
        }
    }
}

@Composable
fun MaterialArticlesList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit
) {
    if (articles.isEmpty()) {
        EmptyScreen()
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {
        items(count = articles.size) { index ->
            articles[index].let { article ->
                MaterialArticleCard(
                    article = article,
                    onClick = { onClick(article) }
                )
            }
        }
    }
}

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit
) {
    if (articles.isEmpty()) {
        EmptyScreen()
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding24dp),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {
        items( count = articles.size) { index ->
            articles[index].let { article ->
                ArticleCard(
                    article = article,
                    onClick = { onClick(article) }
                )
            }
        }
    }
}

@Composable
fun HandlePagingResult(
    articles: LazyPagingItems<Article>
): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }
        error != null -> {
            EmptyScreen(error = error)
            false
        }
        articles.itemCount == 0-> {
            EmptyScreen()
            false
        }
        else -> {
            true
        }
    }
}

@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding24dp)) {
        repeat(20) {
            MaterialArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MediumPadding24dp)
            )
        }
    }
}