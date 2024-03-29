package com.norbertotaveras.android_nova.presentation.screens.sources.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.norbertotaveras.android_nova.domain.model.source.SourceDataModel
import com.norbertotaveras.android_nova.presentation.composables.shared.BoxWithLoading
import com.norbertotaveras.android_nova.presentation.screens.home.screen.AppSwitchToolbar
import com.norbertotaveras.android_nova.presentation.screens.sources.composables.NewsSourceItem
import com.norbertotaveras.android_nova.presentation.screens.sources.state.SourcesViewStateHolder
import com.norbertotaveras.android_nova.presentation.theme.AppColors

@Composable
fun SourceScreen(
    state: SourcesViewStateHolder,
    sources: List<SourceDataModel>,
    navigateToNewsSource: (String, String) -> Unit
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
            LazyColumn {
                items(sources.size) { index ->
                    sources[index].let { source ->
                        NewsSourceItem(
                            source = source,
                            onClick = { navigateToNewsSource(source.name, source.id) }
                        )
                        if (index < sources.size - 1) {
                            Divider()
                        }
                    }
                }
            }
        }
    }
}