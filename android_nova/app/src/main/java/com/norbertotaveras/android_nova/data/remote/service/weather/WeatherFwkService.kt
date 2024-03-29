package com.norbertotaveras.android_nova.data.remote.service.weather

import com.norbertotaveras.android_nova.data.remote.dto.weather.current.CurrentWeatherResponse
import com.norbertotaveras.android_nova.data.remote.dto.weather.forecast.ForecastWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherFwkService {
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("q") location: String
    ): CurrentWeatherResponse

    @GET("forecast.json")
    suspend fun getForecastWeather(
        @Query("q") location: String,
        @Query("days") days: Int,
    ): ForecastWeatherResponse
}