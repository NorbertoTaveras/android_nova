package com.norbertotaveras.android_nova.presentation.screens.home.screen

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.norbertotaveras.android_nova.R
import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.presentation.composables.shared.BoxWithLoading
import com.norbertotaveras.android_nova.presentation.screens.home.composables.ArticlesCarouselWithGHeader
import com.norbertotaveras.android_nova.presentation.screens.home.composables.CategoryListItem
import com.norbertotaveras.android_nova.presentation.screens.home.state.HomeViewStateHolder
import com.norbertotaveras.android_nova.presentation.theme.AppColors
import com.norbertotaveras.android_nova.utils.constants.Const.categories

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeViewStateHolder,
    articles: LazyPagingItems<Article>,
    navigateToDetails: (Article) -> Unit,
    navigateToNewsCategory: (String) -> Unit
) {
    Scaffold(topBar = { AppSwitchToolbar() }) { padding ->
        BoxWithLoading(
            modifier = Modifier
                .background(color = AppColors.parent)
                .padding(top = 64.dp)
                .fillMaxSize()
                .systemBarsPadding(),
            state = state
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = rememberLazyListState(),
                    contentPadding = PaddingValues(bottom = 0.dp)
                ) {
                    item {
                        ArticlesCarouselWithGHeader(
                            articles = articles,
                            listState = LazyListState(
                                firstVisibleItemIndex = 0,
                                firstVisibleItemScrollOffset = 0
                            ),
                            onArticleClicked = { article -> navigateToDetails(article) },
                            onSeeAllClicked = { /*TODO*/ }
                        )
                    }

                    item {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            text = stringResource(R.string.categories),
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontWeight = FontWeight.Bold
                            ),
                        )
                    }

                    item {
                        Card(
                            modifier = Modifier
                                .background(color = AppColors.parent)
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column {
                                categories.forEach { category ->
                                    CategoryListItem(
                                        categoryName = category,
                                        onCategorySelected = { navigateToNewsCategory(category) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AppSwitchToolbar() {
    val coroutineScope = rememberCoroutineScope()
    var switchExpanded by remember { mutableStateOf(false) }
    val backgroundColor =
        animateColorAsState(targetValue = AppColors.parentBottom)
    Column(modifier = Modifier
        .fillMaxWidth()
        .drawBehind {
            drawRect(backgroundColor.value)
        }
    ) {
        Toolbar()
    }
}

@Composable
private fun Toolbar() {
    val backgroundColor =
        animateColorAsState(targetValue = AppColors.parentBottom)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                drawRect(backgroundColor.value)
            }
            .statusBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 13.dp)
                    .width(54.dp)
                    .height(33.dp)
                    .align(Alignment.CenterStart),
            ) {
            }
            AppSwitch(
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Composable
private fun AppSwitch(
    modifier: Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .height(35.dp)
                .width(67.dp),
            painter = rememberAsyncImagePainter(model = R.drawable.news_weather_logo),
            contentScale = ContentScale.Fit,
            contentDescription = null
        )
    }
}