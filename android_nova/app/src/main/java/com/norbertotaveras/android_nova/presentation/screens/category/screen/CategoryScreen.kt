package com.norbertotaveras.android_nova.presentation.screens.category.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.presentation.composables.shared.ArticlesList
import com.norbertotaveras.android_nova.presentation.screens.category.composables.CategoryTopBar
import com.norbertotaveras.android_nova.presentation.screens.category.state.CategoryViewStateHolder
import com.norbertotaveras.android_nova.presentation.screens.category.viewmodel.CategoryViewModel
import com.norbertotaveras.android_nova.presentation.theme.AppColors

@Composable
fun CategoryScreen(
    category: String,
    navigateUp: () -> Unit,
    navigateToDetails: (Article) -> Unit
) {
    val viewModel: CategoryViewModel = hiltViewModel()
    val state: CategoryViewStateHolder = viewModel.viewStateHolder

    Box(
        modifier = Modifier
            .background(color = AppColors.parent)
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
        ) {
            CategoryTopBar(
                title = category,
                onBackClick = navigateUp
            )
            ArticlesList(
                modifier = Modifier
                    .padding(horizontal = 0.dp),
                articles = state.articles.collectAsLazyPagingItems(),
                onClick = { article ->  navigateToDetails(article)}
            )
        }
    }
}