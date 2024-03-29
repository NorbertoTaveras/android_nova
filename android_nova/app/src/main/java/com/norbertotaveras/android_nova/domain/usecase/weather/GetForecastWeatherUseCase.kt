package com.norbertotaveras.android_nova.domain.usecase.weather

import com.norbertotaveras.android_nova.domain.model.forecast.ForecastWeatherResponseDataModel
import com.norbertotaveras.android_nova.domain.repository.weather.WeatherFwkRepository
import javax.inject.Inject

class GetForecastWeatherUseCase @Inject constructor(
    private val repository: WeatherFwkRepository
) {
    suspend operator fun invoke(query: String, days: Int): Result<ForecastWeatherResponseDataModel> {
        return repository.runCatching {
            getForecastWeather(query = query, days = days)
        }.mapCatching {
            Result.success(it.toForecastWeatherResponseDataModel())
        }.getOrElse {
            Result.failure(it)
        }
    }
}