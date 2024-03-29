package com.norbertotaveras.android_nova.domain.model.weather

data class CurrentWeatherResponseDataModel(
    val current: CurrentDataModel,
    val location: LocationDataModel
)
