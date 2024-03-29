package com.norbertotaveras.android_nova.presentation.screens.weather.viewmodel

import androidx.lifecycle.ViewModel
import com.norbertotaveras.android_nova.domain.manager.LocationManager
import com.norbertotaveras.android_nova.domain.usecase.weather.WeatherUseCases
import com.norbertotaveras.android_nova.extensions.launchOnIODispatcher
import com.norbertotaveras.android_nova.presentation.screens.weather.state.WeatherViewStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherUseCases: WeatherUseCases,
    private val locationManager: LocationManager
) : ViewModel() {

    var viewStateHolder = WeatherViewStateHolder()

    init {
        getData()
    }

    private fun getData() {
        launchOnIODispatcher {
            locationManager.latitudeFlow.combine(
                locationManager.longitudeFlow
            ) { latitude, longitude ->
                latitude to longitude
            }.collectLatest { (lat, lon) ->
                if (lat != null && lon != null) {
                    val currentWeather = weatherUseCases.getCurrentWeatherUseCase("$lat,$lon").getOrNull()
                    val forecastWeather = weatherUseCases.getForecastWeatherUseCase("$lat,$lon", 7).getOrNull()

                    val current = currentWeather?.current
                    val location = currentWeather?.location
                    val forecast = forecastWeather?.forecast

                    viewStateHolder.also {
                        it.current = current
                        it.location = location
                        it.forecast = forecast
                        it.isLoading = false
                    }
                }
            }
        }
    }
}