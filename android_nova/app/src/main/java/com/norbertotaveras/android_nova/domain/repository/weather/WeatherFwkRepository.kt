package com.norbertotaveras.android_nova.domain.repository.weather

import com.norbertotaveras.android_nova.data.remote.dto.weather.current.CurrentWeatherResponse
import com.norbertotaveras.android_nova.data.remote.dto.weather.forecast.ForecastWeatherResponse

interface WeatherFwkRepository {
    suspend fun getCurrentWeather(query: String): CurrentWeatherResponse
    suspend fun getForecastWeather(query: String, days: Int): ForecastWeatherResponse
}