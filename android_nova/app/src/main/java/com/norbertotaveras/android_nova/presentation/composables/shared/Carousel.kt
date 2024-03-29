package com.norbertotaveras.android_nova.presentation.composables.shared

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.presentation.theme.AppColors

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalPagerApi
@Composable
fun CarouselStory(
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState(),
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit = {},
    backgroundColor: Color = Color.White
) {
    Column(
        modifier = modifier.drawBehind { drawRect(color = backgroundColor) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(count = articles.itemCount, state = pagerState) { page ->
            val article = articles[page]
            article?.let {
                StoryCellWithHeadline(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(top = 13.dp),
                    article = it,
                    onClick = onClick
                )
            }
        }

        OverflowPagerIndicator(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            pagerState = pagerState,
            visibleIndicators = 5,
            indicatorSize = 8,
            indicatorColor = AppColors.App_Black
        )
    }
}

@Composable
private fun StoryCellWithHeadline(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (Article) -> Unit = {},
) {
    Column(modifier = modifier.clickable { onClick(article) }) {
        StoryCell(article = article)
    }
}