package com.norbertotaveras.android_nova.presentation.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.norbertotaveras.android_nova.R

val fonts = FontFamily(
    Font(resId = R.font.montserrat_regular, weight = FontWeight.Normal),
    Font(resId = R.font.montserrat_bold, weight = FontWeight.Bold),
    Font(resId = R.font.montserrat_medium, weight = FontWeight.Medium),
    Font(resId = R.font.montserrat_semi_bold, weight = FontWeight.SemiBold),
    Font(resId = R.font.montserrat_bolditalic, weight = FontWeight.Bold, style = FontStyle.Italic)
)