package com.norbertotaveras.android_nova.presentation.composables.shared

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale

@Composable
fun RoundedImage(modifier: Modifier = Modifier, imageUrl: String, roundedPercent: Int) {
    ImageFromUrl(
        modifier = modifier.clip(RoundedCornerShape(percent = roundedPercent)),
        url = imageUrl,
        contentScale = ContentScale.Crop
    )
}