package com.norbertotaveras.android_nova.domain.usecase.weather

import com.norbertotaveras.android_nova.domain.model.weather.CurrentWeatherResponseDataModel
import com.norbertotaveras.android_nova.domain.repository.weather.WeatherFwkRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherFwkRepository
) {
    suspend operator fun invoke(query: String): Result<CurrentWeatherResponseDataModel> {
        return repository.runCatching {
            getCurrentWeather(query = query)
        }.mapCatching {
            Result.success(it.toCurrentWeatherResponseDataModel())
        }.getOrElse {
            Result.failure(it)
        }
    }
}