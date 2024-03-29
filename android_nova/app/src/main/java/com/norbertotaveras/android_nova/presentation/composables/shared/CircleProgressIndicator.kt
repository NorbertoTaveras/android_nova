package com.norbertotaveras.android_nova.presentation.composables.shared

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import com.norbertotaveras.android_nova.presentation.theme.AppColors

@Composable
fun SpinningCircleProgressIndicator(
    modifier: Modifier = Modifier,
    staticItemColor: Color = AppColors.primaryColor,
    dynamicItemColor: Color = AppColors.App_Black,
    durationMillis: Int = 1000
) {
    val count = 8

    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = count.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(
        modifier
            .progressSemantics()
            .size(Size)
    ) {

        var canvasWidth = size.width
        var canvasHeight = size.height

        if (canvasHeight < canvasWidth) {
            canvasWidth = canvasHeight
        } else {
            canvasHeight = canvasWidth
        }

        val radius = canvasWidth * .12f

        val horizontalOffset = (size.width - size.height).coerceAtLeast(0f) / 2
        val verticalOffset = (size.height - size.width).coerceAtLeast(0f) / 2
        val center = Offset(
            x = horizontalOffset + canvasWidth - radius,
            y = verticalOffset + canvasHeight / 2
        )

        // Stationary items
        for (i in 0..360 step 360 / count) {
            rotate(i.toFloat()) {
                drawCircle(
                    color = staticItemColor,
                    radius = radius,
                    center = center,
                )
            }
        }

        val coefficient = 360f / count

        // Dynamic items
        for (i in 0..3) {
            rotate((angle.toInt() + i) * coefficient) {
                drawCircle(
                    color = dynamicItemColor.copy(
                        alpha = (i.toFloat() / 4).coerceIn(
                            0f, 1f
                        )
                    ),
                    radius = radius,
                    center = center,
                )
            }
        }
    }
}

/**
 * Indeterminate Material Design spinning progress indicator with circle items that are
 * with each item smaller than next
 * shape.
 * @param color color of the items
 * @param durationMillis duration of one cycle of spinning
 */
@Composable
fun ScaledCircleProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
    durationMillis: Int = 1200
) {
    val count = 8f

    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = count,
        infiniteRepeatable(
            animation = keyframes {
                this.durationMillis = durationMillis
                count * 0.9f at (durationMillis * 0.8f).toInt()
                count * 0.05f at (durationMillis * 0.1f).toInt() with FastOutLinearInEasing
                count * 0.05f at (durationMillis * 0.1f).toInt()
            }
        )
    )

    val radiusList = remember { arrayListOf<Float>() }

    Canvas(
        modifier
            .progressSemantics()
            .size(Size)
    ) {

        var canvasWidth = size.width
        var canvasHeight = size.height

        if (canvasHeight < canvasWidth) {
            canvasWidth = canvasHeight
        } else {
            canvasHeight = canvasWidth
        }

        val coefficient = 360f / count

        val radiusMax = canvasWidth / 2 * .1f

        if (radiusList.isEmpty()) {
            repeat(6) {
                if (it == 0) {
                    radiusList.add(radiusMax)
                } else {
                    radiusList.add(radiusList[it - 1] * 1.2f)
                }
            }
        }

        val radiusDynamicContainer = (canvasWidth - radiusList.last())

        val horizontalOffset = (size.width - size.height).coerceAtLeast(0f) / 2
        val verticalOffset = (size.height - size.width).coerceAtLeast(0f) / 2
        val center =
            Offset(horizontalOffset + radiusDynamicContainer, verticalOffset + canvasHeight / 2)

        // Dynamic items
        for (i in 0 until radiusList.size) {

            val radius = radiusList[i]

            rotate((angle + i) * coefficient) {
                drawCircle(
                    color = color.copy(alpha = (i * .2f).coerceIn(0f, 1f)),
                    radius = radius,
                    center = center,
                )
            }
        }
    }
}

internal val Size = 48.0.dp