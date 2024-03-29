package com.norbertotaveras.android_nova.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val fontBold: TextStyle = TextStyle(
    color = AppColors.App_Black,
    fontSize = 12.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = fonts,
)

val fontSemiBold: TextStyle = TextStyle(
    color = AppColors.App_Black,
    fontSize = 12.sp,
    fontWeight = FontWeight.SemiBold,
    fontFamily = fonts,
)

val fontNormal: TextStyle = TextStyle(
    color = AppColors.App_Black,
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = fonts,
)

val fontMedium: TextStyle = TextStyle(
    color = AppColors.App_Black,
    fontSize = 12.sp,
    fontWeight = FontWeight.Medium,
    fontFamily = fonts,
)