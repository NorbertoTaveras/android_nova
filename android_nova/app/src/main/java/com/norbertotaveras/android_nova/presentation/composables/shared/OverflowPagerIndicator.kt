package com.norbertotaveras.android_nova.presentation.composables.shared

import android.os.Parcelable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.parcelize.Parcelize
import kotlin.math.floor


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OverflowPagerIndicator(
    modifier: Modifier,
    pagerState: PagerState,
    visibleIndicators: Int = 7,
    indicatorSize: Int = 5,
    indicatorColor: Color = Color.White
) {
    OverflowPagerIndicator(
        modifier = modifier,
        numPages = pagerState.pageCount,
        currentPage = pagerState.currentPage,
        visibleIndicators = visibleIndicators,
        indicatorSize = indicatorSize,
        indicatorColor = indicatorColor
    )
}


@Composable
fun OverflowPagerIndicator(
    modifier: Modifier,
    numPages: Int,
    currentPage: Int,
    indicatorSize: Int,
    visibleIndicators: Int,
    indicatorColor: Color
) {
    if (numPages > 0) {
        val visibleIndicator = rememberSaveable {
            VisibleIndicator(
                numPages,
                adjustStart(
                    currentPage = currentPage,
                    pages = numPages,
                    visibleIndicators = visibleIndicators
                ),
                visibleIndicators = visibleIndicators
            )
        }

        visibleIndicator.adjustVisiblePages(currentPage)

        Row(
            modifier = modifier.height(indicatorSize.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (page in 0 until numPages) {
                AnimatedVisibility(
                    visible = visibleIndicator.isPageVisible(page)
                ) {
                    IndicatorCircle(
                        checked = page == currentPage,
                        circleScale = CircleScale.create(
                            visibleIndicator = visibleIndicator,
                            position = visibleIndicator.getPositionByPage(page),
                            visibleIndicators = visibleIndicators
                        ),
                        circleSize = indicatorSize,
                        indicatorColor = indicatorColor
                    )
                }
            }
        }
    }
}

@Composable
private fun IndicatorCircle(
    indicatorColor: Color,
    checked: Boolean,
    circleSize: Int,
    circleScale: CircleScale
) {
    val borderColor = indicatorColor
    val backgroundColor =
        animateColorAsState(targetValue = if (checked) indicatorColor else Color.Transparent)
    val size = animateDpAsState(targetValue = (circleSize * circleScale.scale).dp)
    Box(
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .size(size.value)
            .clip(CircleShape)
            .border(width = 1.dp, color = borderColor, shape = CircleShape)
            .background(backgroundColor.value)
    )
}

@Parcelize
private class VisibleIndicator(
    private val numAllPages: Int,
    private var pageAtStartIndicator: Int,
    private val visibleIndicators: Int
) : Parcelable {
    private val pageAtEndIndicator: Int
        get() = pageAtStartIndicator + visibleIndicators - 1

    val isFirstPageVisible: Boolean
        get() = pageAtStartIndicator == 0

    val isLastPageVisible: Boolean
        get() = pageAtEndIndicator == numAllPages - 1

    fun isPageVisible(page: Int) = page in pageAtStartIndicator..pageAtEndIndicator

    fun getPositionByPage(page: Int) = page - pageAtStartIndicator

    fun adjustVisiblePages(currentPage: Int) {
        when (Position.createOrNull(
            position = getPositionByPage(page = currentPage),
            visibleIndicators = visibleIndicators
        )) {
            Position.SECOND_START -> back()
            Position.SECOND_END -> forward()
            else -> {}
        }
    }

    fun back() {
        if (!isFirstPageVisible) {
            pageAtStartIndicator--
        }
    }

    fun forward() {
        if (!isLastPageVisible) {
            pageAtStartIndicator++
        }
    }

    enum class Position() {
        START,
        SECOND_START,
        SECOND_END,
        END;

        companion object {
            fun createOrNull(position: Int, visibleIndicators: Int) =
                when (position) {
                    0 -> START
                    1 -> SECOND_START
                    visibleIndicators - 2 -> SECOND_END
                    visibleIndicators - 1 -> END
                    else -> null
                }
        }
    }
}

private enum class CircleScale(val scale: Float) {
    LARGE(1f),
    MEDIUM(2 / 3f),
    SMALL(1 / 3f);

    companion object {
        fun create(
            visibleIndicator: VisibleIndicator,
            position: Int,
            visibleIndicators: Int
        ): CircleScale =
            when (VisibleIndicator.Position.createOrNull(
                position = position,
                visibleIndicators = visibleIndicators
            )) {
                VisibleIndicator.Position.START ->
                    if (visibleIndicator.isFirstPageVisible) LARGE else SMALL
                VisibleIndicator.Position.SECOND_START ->
                    if (visibleIndicator.isFirstPageVisible) LARGE else MEDIUM
                VisibleIndicator.Position.SECOND_END ->
                    if (visibleIndicator.isLastPageVisible) LARGE else MEDIUM
                VisibleIndicator.Position.END ->
                    if (visibleIndicator.isLastPageVisible) LARGE else SMALL
                null ->
                    if (position in 0 until visibleIndicators) LARGE else SMALL
            }
    }
}

private fun adjustStart(currentPage: Int, pages: Int, visibleIndicators: Int): Int {
    return when (currentPage) {
        in 0..(visibleIndicators - 1 - 2) -> 0
        in (pages - 1 - visibleIndicators - 1 - 2) until pages -> pages - visibleIndicators
        else -> currentPage - floor(visibleIndicators / 2f).toInt() + 1
    }
}