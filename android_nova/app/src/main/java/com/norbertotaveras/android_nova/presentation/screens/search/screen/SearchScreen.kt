package com.norbertotaveras.android_nova.presentation.screens.search.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.presentation.composables.shared.MaterialArticlesList
import com.norbertotaveras.android_nova.presentation.composables.shared.MaterialSearchBar
import com.norbertotaveras.android_nova.presentation.screens.home.screen.AppSwitchToolbar
import com.norbertotaveras.android_nova.presentation.screens.search.event.SearchEvent
import com.norbertotaveras.android_nova.presentation.screens.search.state.SearchState
import com.norbertotaveras.android_nova.presentation.screens.search.state.SearchViewStateHolder
import com.norbertotaveras.android_nova.presentation.theme.AppColors
import com.norbertotaveras.android_nova.utils.dimens.Dimens.MediumPadding24dp

@Composable
fun SearchScreen(
    state: SearchState,
    event:(SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit
) {
    Scaffold(topBar = { AppSwitchToolbar() }) { padding ->
        Column(
            modifier = Modifier
                .background(color = AppColors.parent)
                .padding(
                    top = 58.dp,
                    start = 4.dp,
                    end = 4.dp
                )
                .statusBarsPadding()
                .fillMaxSize()
        ) {
            MaterialSearchBar(
                query = state.searchQuery,
                readOnly = false,
                onQueryChange = { event(SearchEvent.UpdateSearchQuery(it))},
                onSearch = { event(SearchEvent.SearchNews)}
            )
            Spacer(modifier = Modifier.height(MediumPadding24dp))
            state.articles?.let {
                val articles = it.collectAsLazyPagingItems()
                MaterialArticlesList(
                    articles = articles,
                    onClick = { article ->
                        navigateToDetails(article)
                    }
                )
            }
        }
    }
}