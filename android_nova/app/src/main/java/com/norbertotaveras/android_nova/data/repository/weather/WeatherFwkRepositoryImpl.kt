package com.norbertotaveras.android_nova.data.repository.weather

import com.norbertotaveras.android_nova.data.remote.dto.weather.current.CurrentWeatherResponse
import com.norbertotaveras.android_nova.data.remote.dto.weather.forecast.ForecastWeatherResponse
import com.norbertotaveras.android_nova.data.remote.service.weather.WeatherFwkService
import com.norbertotaveras.android_nova.domain.repository.weather.WeatherFwkRepository

class WeatherFwkRepositoryImpl(
    private val service: WeatherFwkService
) : WeatherFwkRepository {
    override suspend fun getCurrentWeather(query: String): CurrentWeatherResponse {
        return service.getCurrentWeather(location = query)
    }

    override suspend fun getForecastWeather(query: String, days: Int): ForecastWeatherResponse {
        return service.getForecastWeather(location = query, days = days)
    }
}