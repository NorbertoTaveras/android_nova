package com.norbertotaveras.android_nova.presentation.composables.shared

import android.widget.ImageView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Precision
import com.norbertotaveras.android_nova.presentation.theme.AppColors
import com.norbertotaveras.android_nova.utils.base.BaseViewStateHolder
import kotlinx.coroutines.Dispatchers

@Composable
fun BoxWithLoading(
    modifier: Modifier = Modifier,
    state: BaseViewStateHolder,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier) {
        content()
        AnimatedVisibility(visible = state.isLoading, enter = fadeIn(), exit = fadeOut()) {
            ProgressView(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun BoxWithLoading(
    modifier: Modifier = Modifier,
    showLoading: Boolean,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable () -> Unit,
) {
    Box(modifier = modifier, contentAlignment = contentAlignment) {
        content()
        AnimatedVisibility(visible = showLoading, enter = fadeIn(), exit = fadeOut()) {
            ProgressView(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun ProgressView(
    modifier: Modifier,
    backgroundColorProvider: () -> Color = { AppColors.App_Black_a75 },
) {
    Row(
        modifier = modifier
            .background(color = backgroundColorProvider()),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ScaledCircleProgressIndicator(color = AppColors.parent)
    }
}

@Composable
fun ImageFromUrl(
    modifier: Modifier,
    url: String,
    contentScale: ContentScale = ContentScale.Fit
) {
    key(url) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .interceptorDispatcher(Dispatchers.Default)
                .fetcherDispatcher(Dispatchers.IO)
                .decoderDispatcher(Dispatchers.Default)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .precision(Precision.INEXACT)
                .diskCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = null,
            contentScale = contentScale,
            modifier = modifier
        )
    }
}

@Composable
fun ImageViewXml(modifier: Modifier = Modifier, id: Int, tintColor: Color? = null) {
    AndroidView(modifier = modifier, factory = { ctx ->
        ImageView(ctx)
    }) { view ->
        view.setImageResource(id)
        tintColor?.let {
            view.setColorFilter(tintColor.toArgb())
        }
    }
}

fun Modifier.vertical() =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.height, placeable.width) {
            placeable.place(
                x = -(placeable.width / 2 - placeable.height / 2),
                y = -(placeable.height / 2 - placeable.width / 2)
            )
        }
    }