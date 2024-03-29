package com.norbertotaveras.android_nova.presentation.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.norbertotaveras.android_nova.R
import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.presentation.composables.shared.ImageFromUrl
import com.norbertotaveras.android_nova.presentation.theme.AppColors

@Composable
fun ArticlesCarouselWithGHeader(
    articles: LazyPagingItems<Article>,
    listState: LazyListState,
    onSeeAllClicked: () -> Unit,
    onArticleClicked: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 0.dp, bottom = 20.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(start = 19.dp, end = 14.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.overview),
                color = AppColors.App_Black,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Row(
                modifier = Modifier.clickable { onSeeAllClicked() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                /*Text(
                    text = stringResource(id = R.string.see_all_events),
                    style = fontSemiBold.copy(fontSize = 11.sp, color = AppColors.App_Black)
                )
                ImageViewXml(
                    modifier = Modifier
                        .padding(start = 2.dp)
                        .size(20.dp),
                    id = R.drawable.ic_arrow_forward_ios
                )*/
            }
        }
        /*Box(
            modifier = Modifier
                .padding(start = 19.dp, top = 2.dp)
                .fillMaxWidth(0.12f)
                .height(2.dp)
                .drawBehind { drawRect(color = AppColors.App_Gray_606161) })*/

        LazyRow(
            modifier = Modifier.padding(top = 12.dp),
            state = listState,
            contentPadding = PaddingValues(start = 7.dp, end = 7.dp)
        ) {
            items(count = articles.itemCount) { index ->
                articles[index]?.let { article ->
                    CarouselItemAlternate(
                        width = LocalConfiguration.current.screenWidthDp.times(0.85f).dp,
                        article = article,
                        onArticleClicked = onArticleClicked
                    )
                }
            }
        }
    }
}

@Composable
private fun CarouselItem(
    width: Dp,
    article: Article,
    onArticleClicked: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 7.dp)
            .width(width)
            .clickable { onArticleClicked(article) }
    ) {
        Card(
            shape = RoundedCornerShape(4.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            ImageFromUrl(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(750 / 422f),
                contentScale = ContentScale.Crop,
                url = article.urlToImage ?: ""
            )
        }
    }
}

@Composable
private fun CarouselItemAlternate(
    width: Dp,
    article: Article,
    onArticleClicked: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 7.dp)
            .width(width)
            .fillMaxHeight()
            .clickable { onArticleClicked(article) }
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box {
                ImageFromUrl(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(750 / 522f),
                    contentScale = ContentScale.Crop,
                    url = article.urlToImage ?: ""
                )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 1f),
                                    Color.Transparent
                                ),
                                startY = Float.POSITIVE_INFINITY,
                                endY = 0f
                            )
                        )
                )
                Column(
                    modifier = Modifier
                        .matchParentSize()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = article.title ?: "",
                        style = MaterialTheme.typography.titleSmall.copy(color = Color.White),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = article.source.name ?: "",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White.copy(alpha = 0.8f)),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}