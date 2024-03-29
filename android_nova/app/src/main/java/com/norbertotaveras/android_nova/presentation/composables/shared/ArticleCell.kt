package com.norbertotaveras.android_nova.presentation.composables.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.norbertotaveras.android_nova.data.remote.dto.articles.Article

@Composable
fun StoryCell(modifier: Modifier = Modifier, article: Article) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        RoundedImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(700 / 394f),
            imageUrl = article.urlToImage ?: "",
            roundedPercent = 5
        )
    }
}

