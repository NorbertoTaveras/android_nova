package com.norbertotaveras.android_nova.domain.manager

import android.content.Context
import kotlinx.coroutines.flow.Flow

interface LocationManager {
    suspend fun storeLocation(context: Context, latitude: Double, longitude: Double)
    val latitudeFlow: Flow<Double?>
    val longitudeFlow: Flow<Double?>
}