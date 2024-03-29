package com.norbertotaveras.android_nova.presentation.screens.bookmark.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.norbertotaveras.android_nova.R
import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.presentation.composables.shared.ArticlesList
import com.norbertotaveras.android_nova.presentation.composables.shared.MaterialArticlesList
import com.norbertotaveras.android_nova.presentation.screens.bookmark.state.BookmarkState
import com.norbertotaveras.android_nova.presentation.screens.home.screen.AppSwitchToolbar
import com.norbertotaveras.android_nova.utils.dimens.Dimens.MediumPadding24dp

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit
) {
    Scaffold(topBar = { AppSwitchToolbar() }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(top = 58.dp, start = 0.dp, end = 0.dp, bottom = 0.dp)
        ) {

            /*Text(
                modifier = Modifier
                    .padding(start = 20.dp),
                text = "Bookmark",
                style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
                color = colorResource(
                    id = R.color.text_title
                )
            )*/
            Spacer(modifier = Modifier.height(8.dp))
            MaterialArticlesList(
                articles = state.articles,
                onClick = { navigateToDetails(it) }
            )
        }
    }
}