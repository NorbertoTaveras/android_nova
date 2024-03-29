package com.norbertotaveras.android_nova.presentation.screens.onboarding.composables

import androidx.annotation.DrawableRes
import com.norbertotaveras.android_nova.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Today's Headlines & Weather",
        description = "Begin your day informed and prepared. Dive into the latest news and get up-to-date weather reports at a glance.",
        image = R.drawable.news_onboarding
    ),
    Page(
        title = "Weather Alerts & Updates",
        description = "Never get caught in the rain. Stay ahead with real-time weather alerts and forecasts that keep you ready for any day.",
        image = R.drawable.weather_onboarding
    ),
    Page(
        title = "In-depth Reporting & Analysis",
        description = "Go beyond the headlines. Explore comprehensive articles and expert analyses on topics that matter to you.",
        image = R.drawable.newspaper_onboarding
    )
)