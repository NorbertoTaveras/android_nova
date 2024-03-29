package com.norbertotaveras.android_nova.domain.usecase.weather

import com.norbertotaveras.android_nova.domain.usecase.bookmark.DeleteArticleUseCase
import com.norbertotaveras.android_nova.domain.usecase.bookmark.GetArticleUseCase
import com.norbertotaveras.android_nova.domain.usecase.bookmark.GetArticlesUseCase
import com.norbertotaveras.android_nova.domain.usecase.bookmark.UpsertArticleUseCase
import com.norbertotaveras.android_nova.domain.usecase.sources.GetSourcesUseCase
import javax.inject.Inject

data class WeatherUseCases @Inject constructor(
    val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    val getForecastWeatherUseCase: GetForecastWeatherUseCase
)
